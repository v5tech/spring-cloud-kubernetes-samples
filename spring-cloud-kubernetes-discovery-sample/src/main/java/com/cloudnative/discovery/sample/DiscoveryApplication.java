package com.cloudnative.discovery.sample;

import com.cloudnative.discovery.sample.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryApplication {

    private final String hostName = System.getenv("HOSTNAME");

    @Autowired
    private FeignService feignService;

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @GetMapping("/info")
    public String invokeInfo() {
        return feignService.info();
    }

    @GetMapping(value = {"/services", "/services/{serviceId}"})
    public List<String> invokeServices(@PathVariable("serviceId") String serviceId) {
        return feignService.services(serviceId);
    }
}
