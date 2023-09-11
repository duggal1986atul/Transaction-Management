package com.converter.currency.TransactionManagement.controller;

import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.service.TransactionService;
import com.converter.currency.openapi.api.PurchaseApi;
import com.converter.currency.openapi.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

public class TransactionController implements PurchaseApi {

    @Autowired
    TransactionService transactionService;

    @Override
    public ResponseEntity<Transaction> getTransaction(Long id, String country) {
        Transaction transaction = new Transaction();
        return new ResponseEntity(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addTransaction(Transaction transaction) {
        PurchaseTransactionEntity purchaseTransactionEntity = transactionService.save(transaction);
        if(ObjectUtils.isEmpty(purchaseTransactionEntity))
            new ResponseEntity("Some Internal Server error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity("Created Successfully", HttpStatus.CREATED);
    }
}