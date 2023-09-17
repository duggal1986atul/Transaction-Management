package com.converter.currency.TransactionManagement.dto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class FiscalDataResponseDto {
    private List<Data> data;
}
