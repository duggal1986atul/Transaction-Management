package com.converter.currency.TransactionManagement.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Data
@Setter
@NoArgsConstructor
public class TransactionRequestDto {
    @Size(max = 50, message
            = "description must be max 50 characters")
    private String description;
    @PastOrPresent
    private LocalDate transactionDate;
    @NotNull
    private BigDecimal amount;
}
