package com.cloudnative.config.sample;

import com.cloudnative.config.sample.config.ConfigMapRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configmap")
public class ConfigMapController {

    @Value("${greeting.message}")
    private String message;

    @Autowired
    private ConfigMapRead configMapRead;

    @Autowired
    private Environment environment;

    @GetMapping("/value")
    public String value() {
        return message;
    }

    @GetMapping("/properties")
    public String properties() {
        return configMapRead.getMessage();
    }

    @GetMapping("/env")
    public String env() {
        return environment.getProperty("greeting.message");
    }


}
