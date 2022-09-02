package com.cloudnative.svcconfig.sample;

import com.cloudnative.svcconfig.sample.config.SecretRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
public class SecretController {

    @Value("${secret.username}")
    private String username;

    @Value("${secret.password}")
    private String password;

    @Autowired
    private SecretRead secretRead;

    @Autowired
    private Environment environment;

    @GetMapping("/value")
    public String value() {
        return username + "|" + password;
    }

    @GetMapping("/properties")
    public String properties() {
        return secretRead.getUsername() + "|" + secretRead.getPassword();
    }

    @GetMapping("/env")
    public String env() {
        return environment.getProperty("secret.username") + "|" + environment.getProperty("secret.password");
    }

}
