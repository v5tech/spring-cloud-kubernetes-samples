package com.cloudnative.discovery.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "weibo-service",url = "${feign.client.weibo-service-url:http://weibo-service:5000}")
public interface WeiboService {

    @GetMapping("/")
    Object news();

    @GetMapping("/info")
    String info();

}
