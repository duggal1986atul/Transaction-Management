package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.exception.ServerSideException;
import com.converter.currency.TransactionManagement.dto.FiscalDataResponseDTO;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDTO;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import com.converter.currency.TransactionManagement.client.FiscalClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TransactionServiceImpl implements TransactionService{


    private final TransactionRepository transactionRepository;

    private final FiscalClient fiscalClient;

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, FiscalClient fiscalClient, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.fiscalClient = fiscalClient;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public TransactionResponseDTO getTransaction(Integer id, String countryCode)throws PurchaseTransactionNotFoundException {
        log.info("calculate currency exchange for id:-{} countrycode:-{}",id,countryCode);

        Optional<PurchaseTransactionEntity> purchaseTransactionEntity = transactionRepository.findById(id);

        if(purchaseTransactionEntity.isEmpty()|| ObjectUtils.isEmpty(purchaseTransactionEntity.get().getTransactionDate())){
            log.error("purchase transaction not available for rest client look up {}",id);
            throw new PurchaseTransactionNotFoundException("Purchase transaction not available for calculations:->"+id);
        }

        FiscalDataResponseDTO responseDto = fiscalClient.connectToFiscalUrl(purchaseTransactionEntity.get().getTransactionDate(), countryCode);

        if(ObjectUtils.isEmpty(responseDto)||ObjectUtils.isEmpty(responseDto.getData())||responseDto.getData().isEmpty()||ObjectUtils.isEmpty(responseDto.getData().get(0).getExchange_rate())){
            log.error("exchange rate not available for rest client look up {}",id);
            throw new PurchaseTransactionNotFoundException("exchange rate not available for rest client look up->"+id);
        }

        return transactionMapper.mapToResponseDto(purchaseTransactionEntity.get(),responseDto.getData().get(0).getExchange_rate());
    }

    @Override
    public PurchaseTransactionEntity save(TransactionRequestDTO transactionRequestDto) throws ServerSideException {
        log.info("inside service layer to save transaction{}",transactionRequestDto);
        try {
            return  transactionRepository.save(transactionMapper.map(transactionRequestDto));

        }catch (Exception exception) {
            log.error("An exception has occured will setting to database for the request{}",transactionRequestDto);
            throw new ServerSideException(exception.getMessage());
        }
    }
 }
