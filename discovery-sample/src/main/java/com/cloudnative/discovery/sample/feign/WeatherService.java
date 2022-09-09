package com.cloudnative.discovery.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "weather-service",url = "${feign.client.weather-service-url:http://weather-service:8080}")
public interface WeatherService {

    @GetMapping("/")
    Object weatherInfo();

}
