package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;

public interface TransactionService {
    public TransactionRequestDto getTransaction(Long id, String countryCode);
    public PurchaseTransactionEntity save(TransactionRequestDto transactionRequestDto);
}
