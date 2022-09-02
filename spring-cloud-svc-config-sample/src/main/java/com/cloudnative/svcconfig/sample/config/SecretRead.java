package com.cloudnative.svcconfig.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "secret")
public class SecretRead {
 
    private String username;
    private String password;

}