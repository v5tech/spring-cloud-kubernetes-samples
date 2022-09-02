package com.cloudnative.discovery.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @GetMapping("/")
    public Object index(){
        return discoveryClient.getServices().stream().map(serviceId->{
            Map<String,Object> map = new HashMap<>();
            map.put("serviceId",serviceId);
            map.put("serviceInstance",discoveryClient.getInstances(serviceId));
            return map;
        }).collect(Collectors.toList());
    }

}
