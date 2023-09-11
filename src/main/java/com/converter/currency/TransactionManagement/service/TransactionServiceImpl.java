package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import com.converter.currency.openapi.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    TransactionMapper transactionMapper;


    @Override
    public Transaction getTransaction(Long id, String countryCode) {
        return null;
    }

    @Override
    public PurchaseTransactionEntity save(Transaction transactionDto) {

        PurchaseTransactionEntity transaction = transactionMapper.map(transactionDto);
        return transactionRepository.save(transaction);
    }
}
