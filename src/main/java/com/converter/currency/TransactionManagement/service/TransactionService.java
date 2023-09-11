package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.openapi.model.Transaction;

public interface TransactionService {
    public Transaction getTransaction(Long id,String countryCode);
    public PurchaseTransactionEntity save(Transaction transactionDto);
}
