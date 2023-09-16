package com.converter.currency.TransactionManagement.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Data
@Setter
@NoArgsConstructor
public class TransactionResponseDTO {
    private LocalDate transactionDate;
    private String description;
    private BigDecimal amount;
    private  BigDecimal convertedAmount;
    private Integer identifier;
    private BigDecimal exchangeRate;
}
