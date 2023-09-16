package com.converter.currency.TransactionManagement.mapper;


import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionMapper {
    public PurchaseTransactionEntity map(TransactionRequestDto transactionDtoRequestRequestDto) {
        log.info("inside service layer to save transaction{}", transactionDtoRequestRequestDto);
        PurchaseTransactionEntity transaction = new PurchaseTransactionEntity();
        transaction.setDescription(transactionDtoRequestRequestDto.getDescription());
        transaction.setTransactionDate(transactionDtoRequestRequestDto.getTransactionDate());
        transaction.setAmount(transactionDtoRequestRequestDto.getAmount());
        return transaction;
    }
}
