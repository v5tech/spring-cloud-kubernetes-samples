package com.cloudnative.discovery.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "config-sample",url = "${feign.client.config-sample-url:http://config-sample:8080}")
public interface FeignService {

    @GetMapping("/info")
    String info();

    @GetMapping(value = {"/services/{serviceId}"})
    List<String> services(@PathVariable(required = false) String serviceId);

}
