package com.converter.currency.TransactionManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@lombok.Data
@Setter
@NoArgsConstructor
public class Data {
    private LocalDate record_date;
    private String country;
    private String currency;
    private Double exchange_rate;

}
