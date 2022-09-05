package com.cloudnative.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    private final String hostName = System.getenv("HOSTNAME");

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/config-sample/**").filters( f ->
                        f.circuitBreaker(c -> c.setName("config-sample").setFallbackUri("forward:/fallback"))).uri("lb://spring-cloud-kubernetes-config-sample"))
                .build();
    }

    @GetMapping("/fallback")
    public ResponseEntity<Object> fallback() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("fallback", "true");
        return ResponseEntity.ok().headers(headers).body(Collections.emptyList());
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


}
