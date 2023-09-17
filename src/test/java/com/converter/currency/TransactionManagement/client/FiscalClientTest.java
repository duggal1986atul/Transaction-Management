package com.converter.currency.TransactionManagement.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.converter.currency.TransactionManagement.configuration.FiscalUrlConfig;
import com.converter.currency.TransactionManagement.dto.Data;
import com.converter.currency.TransactionManagement.dto.FiscalDataResponseDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class FiscalClientTest {
    @InjectMocks
    private FiscalClient fiscalClient;

    @Mock
    private FiscalUrlConfig fiscalUrlConfig;

    @Mock
    private RestTemplate restTemplate;

    public FiscalClientTest() {
    }
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void connectToFiscalUrlTestNullResponse(){
        when(fiscalUrlConfig.getUrl()).thenReturn("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?fields=record_date,country,currency,exchange_rate&");
        Mockito
                .when(restTemplate.getForEntity(
                        any(), any()))
          .thenReturn(new ResponseEntity(getFiscalData(), HttpStatus.OK));

        FiscalDataResponseDto responseDto = fiscalClient.connectToFiscalUrl(LocalDate.now(),"India");

        assertNull(responseDto);
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
}
