package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.Exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.Exception.ServerSideException;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TransactionServiceImpl implements TransactionService{


    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public TransactionResponseDTO getTransaction(Integer id, String countryCode)throws PurchaseTransactionNotFoundException {
        Optional<PurchaseTransactionEntity> purchaseTransactionEntity = transactionRepository.findById(id);
        if(purchaseTransactionEntity.isEmpty()){
            log.error("purchase transaction not available for rest client look up {}",id);
            throw new PurchaseTransactionNotFoundException("Purchase transaction not available for calculations:->"+id);
        }
        return transactionMapper.mapToResponseDto(purchaseTransactionEntity.get());
    }

    @Override
    public PurchaseTransactionEntity save(TransactionRequestDto transactionRequestDto) throws ServerSideException {
        log.info("inside service layer to save transaction{}",transactionRequestDto);
        try {
            return  transactionRepository.save(transactionMapper.map(transactionRequestDto));

        }catch (Exception exception) {
            log.error("An exception has occured will setting to database for the request{}",transactionRequestDto);
            throw new ServerSideException(exception.getMessage());
        }
    }
}
