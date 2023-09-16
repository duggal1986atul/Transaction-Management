package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.Exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.Exception.ServerSideException;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;

public interface TransactionService {
    public TransactionResponseDTO getTransaction(Integer id, String countryCode) throws PurchaseTransactionNotFoundException;
    public PurchaseTransactionEntity save(TransactionRequestDto transactionRequestDto) throws ServerSideException;
}
