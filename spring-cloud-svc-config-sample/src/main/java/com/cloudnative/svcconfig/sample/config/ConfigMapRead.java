package com.cloudnative.svcconfig.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "greeting")
public class ConfigMapRead {

    private String message;

}
