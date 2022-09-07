package com.cloudnative.discovery.sample;

import com.cloudnative.discovery.sample.feign.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryApplication {

    private final String hostName = System.getenv("HOSTNAME");

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignService feignService;

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @GetMapping("/info")
    public String invokeInfo() {
        return String.format("invoke by %s，receive data: %s", hostName, feignService.info());
    }

    @GetMapping(value = {"/services", "/services/{serviceId}"})
    public List<String> invokeServices(@PathVariable("serviceId") String serviceId) {
        return feignService.services(serviceId);
    }

    @GetMapping("/r_info")
    public String info(){
        String serviceUrl = environment.getProperty("feign.client.config-sample-url","http://config-sample:8080");
        return restTemplate.getForObject(serviceUrl+"/info",String.class);
    }

}
