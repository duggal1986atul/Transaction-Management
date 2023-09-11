package com.converter.currency.TransactionManagement.mapper;


import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.openapi.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TransactionMapper {
    public PurchaseTransactionEntity map(Transaction transactionRequestDto) {
        PurchaseTransactionEntity transaction = new PurchaseTransactionEntity();
        transaction.setIdentifier(transactionRequestDto.getIdentifier());
        transaction.setDescription(transactionRequestDto.getDescription());
        transaction.setTransactionDate(transactionRequestDto.getTransactionDate());
        transaction.setAmount(BigDecimal.valueOf(transactionRequestDto.getAmount()).setScale(2, RoundingMode.HALF_UP));
        return transaction;
    }
}
