package com.converter.currency.TransactionManagement.controller;

import com.converter.currency.TransactionManagement.Exception.PurchaseTransactionNotFoundException;
import com.converter.currency.TransactionManagement.Exception.ServerSideException;
import com.converter.currency.TransactionManagement.dto.TransactionRequestDto;
import com.converter.currency.TransactionManagement.dto.TransactionResponseDTO;
import com.converter.currency.TransactionManagement.entity.PurchaseTransactionEntity;
import com.converter.currency.TransactionManagement.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/transactions/{transactionId}/country/{country}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable Integer transactionId, @PathVariable String country) throws PurchaseTransactionNotFoundException {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        if (ObjectUtils.isEmpty(transactionId) || ObjectUtils.isEmpty(country)|| StringUtils.containsWhitespace(country)) {
            log.error("invalid request params ");
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }
        try {
            responseDTO = transactionService.getTransaction(transactionId, country);
        } catch (PurchaseTransactionNotFoundException e) {
            log.error("Resource not found {}", e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity(responseDTO, HttpStatus.OK);
      }

    @PostMapping(path = "/addTransactions",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) throws ServerSideException {
        log.info("request Dto in controller layer {}", transactionRequestDto);
        PurchaseTransactionEntity purchaseTransactionEntity = transactionService.save(transactionRequestDto);
        if(ObjectUtils.isEmpty(purchaseTransactionEntity))
            new ResponseEntity("Some Internal Server error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity("Record Created Successfully", HttpStatus.CREATED);
    }
}