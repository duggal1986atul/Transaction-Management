package com.converter.currency.TransactionManagement.service;

import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper mapper;

    TransactionRequestDto transactionRequestDto;

    PurchaseTransactionEntity purchaseTransactionEntity;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    private TransactionRequestDto getValidRequestBody() {
        transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setTransactionDate(LocalDate.now());
        transactionRequestDto.setDescription("test currency");
        transactionRequestDto.setAmount(new BigDecimal(50.0));
        return transactionRequestDto;
    }


    private PurchaseTransactionEntity getPurchaseTransactionEntity() {
        purchaseTransactionEntity = new PurchaseTransactionEntity();
        purchaseTransactionEntity.setAmount(new BigDecimal(50.0));
        purchaseTransactionEntity.setIdentifier(1);
        purchaseTransactionEntity.setTransactionDate(LocalDate.now());
        purchaseTransactionEntity.setDescription("test currency");
        return purchaseTransactionEntity;
    }

    @Test
    public void saveTransactionTest() throws  Exception{
        when(mapper.map(getValidRequestBody())).thenReturn(getPurchaseTransactionEntity());
        when(transactionRepository.save(getPurchaseTransactionEntity())).thenReturn(getPurchaseTransactionEntity());
        PurchaseTransactionEntity purchaseTransactionEntity = transactionService.save(getValidRequestBody());
        assertNotNull(purchaseTransactionEntity);
    }
}
