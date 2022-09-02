package com.cloudnative.svcdiscovery.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SvcDiscoverySampleApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(SvcDiscoverySampleApplication.class, args);
    }

    @GetMapping("/")
    public Object index(){
        List<ServiceInstance> instances = new ArrayList<>();
        for (String service : discoveryClient.getServices()) {
            instances.addAll(discoveryClient.getInstances(service));
        }
        return instances;
    }

}
