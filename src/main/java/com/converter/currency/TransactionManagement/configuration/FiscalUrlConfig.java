package com.converter.currency.TransactionManagement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fiscal")
@Getter
@Setter
public class FiscalUrlConfig {
    private String url;
}
