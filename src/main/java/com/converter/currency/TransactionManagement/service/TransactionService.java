package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.exception.ServerSideException;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDTO;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;

public interface TransactionService {
    public TransactionResponseDTO getTransaction(Integer id, String countryCode) throws PurchaseTransactionNotFoundException;
    public PurchaseTransactionEntity save(TransactionRequestDTO transactionRequestDto) throws ServerSideException;
}
