package com.converter.currency.TransactionManagement.dto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class FiscalDataResponseDTO {
    private List<Data> data;
}
