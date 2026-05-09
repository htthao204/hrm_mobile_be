package com.example.hrm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "company")
public class CompanyLocationConfig {

    private Double latitude;

    private Double longitude;

    private Double allowedRadiusMeter;
}