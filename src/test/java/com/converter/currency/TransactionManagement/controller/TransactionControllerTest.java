package com.converter.currency.TransactionManagement.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.converter.currency.TransactionManagement.dto.TransactionRequestDTO;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;


@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    TransactionService transactionService;


    @Autowired
    private ObjectMapper objectMapper;

    TransactionRequestDTO transactionRequestDto;

    PurchaseTransactionEntity purchaseTransactionEntity;

    TransactionResponseDTO responseDTO;

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
    public void testGetPurchaseTransactionSuccess() throws Exception {
        given(transactionService.getTransaction(1,"India")).willReturn(getPurchaseResponseDto());
        mvc.perform(get("/transactions/{transactionId}/country/{country}",1,"India")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPurchaseTransactionBadRequest() throws Exception {
        mvc.perform(get("/transactions/{transactionId}/country/{country}",1," ").accept(MediaType.APPLICATION_JSON))
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
    private TransactionRequestDTO setValidRequestBody() {
        transactionRequestDto = new TransactionRequestDTO();
        transactionRequestDto.setTransactionDate(LocalDate.now());
        transactionRequestDto.setDescription("test currency");
        transactionRequestDto.setAmount(new BigDecimal(50.0));
        return transactionRequestDto;
    }

    private TransactionRequestDTO setInvalidRequestBody() {
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
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

    private TransactionResponseDTO getPurchaseResponseDto() {
        responseDTO = new TransactionResponseDTO();
        //responseDTO.setAmount(10.0);
        responseDTO.setAmount(new BigDecimal(50.0));
        responseDTO.setIdentifier(1);
        responseDTO.setTransactionDate(LocalDate.now());
        responseDTO.setDescription("test currency");
        return responseDTO;
    }
}
