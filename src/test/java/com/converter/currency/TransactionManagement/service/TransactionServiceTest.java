package com.converter.currency.TransactionManagement.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.converter.currency.TransactionManagement.exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.dto.Data;
import com.converter.currency.TransactionManagement.dto.FiscalDataResponseDto;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.mapper.TransactionMapper;
import com.converter.currency.TransactionManagement.repository.TransactionRepository;
import com.converter.currency.TransactionManagement.utility.FiscalClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper mapper;

    @Mock
    private FiscalClient fiscalClient;

    TransactionRequestDto transactionRequestDto;

    PurchaseTransactionEntity purchaseTransactionEntity;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void saveTransactionTest() throws  Exception{
        when(mapper.map(getValidRequestBody())).thenReturn(getPurchaseTransactionEntity());
        when(transactionRepository.save(getPurchaseTransactionEntity())).thenReturn(getPurchaseTransactionEntity());
        PurchaseTransactionEntity purchaseTransactionEntity = transactionService.save(getValidRequestBody());
        assertNotNull(purchaseTransactionEntity);
    }

    @Test
    public void getTransactionSuccess() throws  Exception{
        when(transactionRepository.findById(1)).thenReturn(Optional.of(getPurchaseTransactionEntity()));
        when(fiscalClient.connectToFiscalUrl(LocalDate.now(),"India")).thenReturn(getFiscalData());
        when(mapper.mapToResponseDto(getPurchaseTransactionEntity(),getFiscalData().getData().get(0).getExchange_rate())).thenReturn(getTransactionResponseDto());
        TransactionResponseDTO responseDTO = transactionService.getTransaction(1,"India");
        assertNotNull(responseDTO);
    }

    @Test
    public void getTransactionFailedScenarioRepoEmpty() throws  Exception{
        PurchaseTransactionNotFoundException thrown = Assertions.assertThrows(PurchaseTransactionNotFoundException.class, () -> {
            when(transactionRepository.findById(1)).thenReturn(Optional.empty());
            TransactionResponseDTO responseDTO = transactionService.getTransaction(1,"India");

        });
        Assertions.assertEquals("Purchase transaction not available for calculations:->1", thrown.getMessage());
    }

    @Test
    public void getTransactionFailedScenarioUrlClientFallout() throws  Exception{
        PurchaseTransactionNotFoundException thrown = Assertions.assertThrows(PurchaseTransactionNotFoundException.class, () -> {
            when(transactionRepository.findById(1)).thenReturn(Optional.of(getPurchaseTransactionEntity()));
            when(fiscalClient.connectToFiscalUrl(LocalDate.now(),"India")).thenReturn(null);
            TransactionResponseDTO responseDTO = transactionService.getTransaction(1,"India");
        });
        Assertions.assertEquals("exchange rate not available for rest client look up->1", thrown.getMessage());
    }

    private TransactionRequestDto getValidRequestBody() {
        transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setTransactionDate(LocalDate.now());
        transactionRequestDto.setDescription("test currency");
        transactionRequestDto.setAmount(new BigDecimal("50.0"));
        return transactionRequestDto;
    }

    private FiscalDataResponseDto getFiscalData() {
        FiscalDataResponseDto dto = new FiscalDataResponseDto();
        List<Data> dataList = new ArrayList<>();
        Data data = new Data();
        data.setCountry("India");
        data.setCurrency("INR");
        data.setExchange_rate(new BigDecimal("82.599"));
        data.setRecord_date(LocalDate.now());
        dataList.add(data);
        dto.setData(dataList);
        return dto;
    }

    private TransactionResponseDTO getTransactionResponseDto() {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setAmount(new BigDecimal("50.0"));
        responseDTO.setDescription("currency test");
        responseDTO.setConvertedAmount(new BigDecimal(4130));
        return responseDTO;

    }

    private PurchaseTransactionEntity getPurchaseTransactionEntity() {
        purchaseTransactionEntity = new PurchaseTransactionEntity();
        purchaseTransactionEntity.setAmount(new BigDecimal("50.0"));
        purchaseTransactionEntity.setIdentifier(1);
        purchaseTransactionEntity.setTransactionDate(LocalDate.now());
        purchaseTransactionEntity.setDescription("test currency");
        return purchaseTransactionEntity;
    }
}
