package com.converter.currency.TransactionManagement.client;

import com.converter.currency.TransactionManagement.configuration.FiscalUrlConfig;
import com.converter.currency.TransactionManagement.dto.FiscalDataResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
@Slf4j
public class FiscalClient {
    private final RestTemplate restTemplate;

    private final FiscalUrlConfig fiscalUrlConfig;

    private String compositeUrl;

    public FiscalClient(RestTemplate restTemplate, FiscalUrlConfig fiscalUrlConfig) {
        this.restTemplate = restTemplate;
        this.fiscalUrlConfig = fiscalUrlConfig;
    }
    public FiscalDataResponseDto connectToFiscalUrl(final LocalDate recordDate, final String country){

        ResponseEntity<FiscalDataResponseDto> responseDto;
        LocalDate filterDate = recordDate.minusMonths(6);

        compositeUrl = fiscalUrlConfig.getUrl() + "filter=record_date:gte:" + filterDate + ",country:eq:" + country;

        try {
            log.info("invoking url for filterDate {}",filterDate);
            responseDto = restTemplate.getForEntity(compositeUrl, FiscalDataResponseDto.class);


        } catch (RestClientResponseException exception) {
            log.error("An error has occured while connecting to url {}", compositeUrl);
            responseDto = new ResponseEntity<>(HttpStatusCode.valueOf(exception.getRawStatusCode()));
        }
        if(!ObjectUtils.isEmpty(responseDto)&& !ObjectUtils.isEmpty(responseDto.getBody())) {
            log.info("responseDto.getBody() {}", responseDto.getBody());
            return responseDto.getBody();
        }
        return null;
    }
 }
