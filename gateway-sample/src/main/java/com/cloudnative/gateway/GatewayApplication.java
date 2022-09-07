package com.cloudnative.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

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
                .build();
    }

    @GetMapping("/fallback")
    public ResponseEntity<Object> fallback() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("fallback", "true");
        return ResponseEntity.ok().headers(headers).body("服务不可用，请稍后再试！");
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Scheduled(cron = "0/3 * * ? * *")
    public void echo() {
        System.out.println(System.getenv("HOSTNAME")+": "+environment.getProperty("message"));
    }

}
