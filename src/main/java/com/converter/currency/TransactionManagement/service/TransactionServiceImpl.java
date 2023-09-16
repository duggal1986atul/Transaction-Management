package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TransactionRequestDto getTransaction(Long id, String countryCode) {
        return null;
    }

    @Override
    public PurchaseTransactionEntity save(TransactionRequestDto transactionRequestDto) {
        PurchaseTransactionEntity transaction = transactionMapper.map(transactionRequestDto);
        log.info("inside service layer to save transaction{}",transaction);
        return transactionRepository.save(transaction);
    }
}
