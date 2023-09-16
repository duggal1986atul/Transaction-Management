package com.converter.currency.TransactionManagement.controller;

import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    TransactionService transactionService;


    @Autowired
    private ObjectMapper objectMapper;

    TransactionRequestDto transactionRequestDto;

    PurchaseTransactionEntity purchaseTransactionEntity;
    @BeforeEach
    public void setup() {

    }

    @Test
    public void testPostTransactionsBadRequest() throws Exception {
        String body = objectMapper.writeValueAsString(setInvalidRequestBody());
        mvc.perform(post("/addTransactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                //Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPostTransactionsSuccess() throws Exception {
        String body = objectMapper.writeValueAsString(setValidRequestBody());
        given(transactionService.save(setValidRequestBody())).willReturn(getPurchaseTransactionEntity());
        mvc.perform(post("/addTransactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                //Then
                .andExpect(status().is2xxSuccessful());
    }
    private TransactionRequestDto setValidRequestBody() {
        transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setTransactionDate(LocalDate.now());
        transactionRequestDto.setDescription("test currency");
        transactionRequestDto.setAmount(new BigDecimal(100));
        return transactionRequestDto;
    }

    private TransactionRequestDto setInvalidRequestBody() {
        TransactionRequestDto requestDTO = new TransactionRequestDto();
        requestDTO.setAmount(null);
        return  requestDTO;
    }

    private PurchaseTransactionEntity getPurchaseTransactionEntity() {
        purchaseTransactionEntity = new PurchaseTransactionEntity();
        purchaseTransactionEntity.setAmount(new BigDecimal(50.0));
        purchaseTransactionEntity.setIdentifier(1);
        purchaseTransactionEntity.setTransactionDate(LocalDate.now());
        purchaseTransactionEntity.setDescription("test currency");
        return purchaseTransactionEntity;
    }
}