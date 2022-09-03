package com.cloudnative.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    private final String hostName = System.getenv("HOSTNAME");

//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p.path("/hotdeals**").filters( f ->
//                        f.circuitBreaker(c -> c.setName("hotdeals").setFallbackUri("forward:/fallback"))).uri("lb://hot-deals"))
//                .route(p -> p.path("/fashion/**").filters(f ->
//                        f.circuitBreaker(c -> c.setName("fashion").setFallbackUri("forward:/fallback"))).uri("lb://fashion-bestseller"))
//                .route(p -> p.path("/toys/**").filters(f ->
//                        f.circuitBreaker(c -> c.setName("toys").setFallbackUri("forward:/fallback"))).uri("lb://toys-bestseller"))
//                .build();
//    }

//    @GetMapping("/fallback")
//    public ResponseEntity<Object> fallback() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("fallback", "true");
//        return ResponseEntity.ok().headers(headers).body(Collections.emptyList());
//    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


}
