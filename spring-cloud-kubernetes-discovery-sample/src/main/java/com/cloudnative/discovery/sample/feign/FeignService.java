package com.cloudnative.discovery.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "spring-cloud-kubernetes-config-sample",url = "http://192.168.101.74:31528")
//@FeignClient(value = "spring-cloud-kubernetes-config-sample")
public interface FeignService {

    @GetMapping("/info")
    String info();

    @GetMapping(value = {"/services/{serviceId}"})
    List<String> services(@PathVariable(required = false) String serviceId);

}
