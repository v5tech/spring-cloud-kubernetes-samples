package com.cloudnative.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableScheduling
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private Environment environment;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/config-sample/**")
                        .filters(f -> f
                                // 去前缀
                                .stripPrefix(1)
                                // 设置熔断
                                .circuitBreaker(c -> c.setName("config-sample").setFallbackUri("forward:/fallback")))
                        .uri("lb://config-sample"))
                .route(p -> p.path("/discovery-sample/**")
                        .filters(f -> f
                                // 去前缀
                                .stripPrefix(1)
                                // 设置熔断
                                .circuitBreaker(c -> c.setName("discovery-sample").setFallbackUri("forward:/fallback")))
                        .uri("lb://discovery-sample"))
                .route(p -> p.path("/news/**")
                        .filters(f -> f
                                // 去前缀
                                .stripPrefix(1)
                                // 设置熔断
                                .circuitBreaker(c -> c.setName("weibo-news").setFallbackUri("forward:/fallback")))
                        .uri("lb://weibo-news"))
                .route(p -> p.path("/weather/**")
                        .filters(f -> f
                                // 去前缀
                                .stripPrefix(1)
                                // 设置熔断
                                .circuitBreaker(c -> c.setName("weather-service").setFallbackUri("forward:/fallback")))
                        .uri("lb://weather-service"))
                .build();
    }

    @GetMapping("/fallback")
    public ResponseEntity<Object> fallback() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("fallback", "true");
        Map<String,Object> map = new HashMap<>();
        map.put("code",503);
        map.put("msg","服务不可用，请稍后再试！");
        return ResponseEntity.ok().headers(headers).body(map);
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Scheduled(cron = "0/30 * * ? * *")
    public void echo() {
        log.info("{}：{}",System.getenv("HOSTNAME"), environment.getProperty("message"));
    }

    @Scheduled(cron = "*/10 * * * * ?")
    protected void refreshRoute() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
