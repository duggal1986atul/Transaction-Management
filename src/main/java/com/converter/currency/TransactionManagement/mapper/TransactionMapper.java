package com.converter.currency.TransactionManagement.mapper;


import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class TransactionMapper {
    public PurchaseTransactionEntity map(final TransactionRequestDto transactionDtoRequestRequestDto) {
        PurchaseTransactionEntity transaction = new PurchaseTransactionEntity();
        transaction.setDescription(transactionDtoRequestRequestDto.getDescription());
        transaction.setTransactionDate(transactionDtoRequestRequestDto.getTransactionDate());
        transaction.setAmount(transactionDtoRequestRequestDto.getAmount());
        return transaction;
    }

    public TransactionResponseDTO mapToResponseDto(final PurchaseTransactionEntity purchaseTransactionEntity)
    {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setIdentifier(purchaseTransactionEntity.getIdentifier());
        transactionResponseDTO.setDescription(purchaseTransactionEntity.getDescription());
        transactionResponseDTO.setExchangeRate(new BigDecimal(82.90));
        transactionResponseDTO.setAmount(purchaseTransactionEntity.getAmount());
        return transactionResponseDTO;

    }
}
