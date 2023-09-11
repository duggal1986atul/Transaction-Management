package com.converter.currency.TransactionManagement.controller;

import com.converter.currency.openapi.api.PurchaseApi;
import com.converter.currency.openapi.model.Transaction;
import org.springframework.http.ResponseEntity;

public class TransactionController implements PurchaseApi {

    @Override
    public ResponseEntity<Transaction> getTransaction(Long id, String country) {
    }

    @Override
    public ResponseEntity<String> addTransaction(Transaction transaction) {

    }
}