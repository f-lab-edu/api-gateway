package com.moondysmell.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        //System.setProperty("reactor.netty.http.server.accessLogEnabled","true");
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

//    @Bean
//    public ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory(CircuitBreakerRegistry circuitBreakerRegistry) {
//        ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory = new ReactiveResilience4JCircuitBreakerFactory();
//        reactiveResilience4JCircuitBreakerFactory.configureCircuitBreakerRegistry(circuitBreakerRegistry);
//        return reactiveResilience4JCircuitBreakerFactory;
//    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(5)
//                .permittedNumberOfCallsInHalfOpenState(2)
//                .slidingWindowSize(2)
//                .minimumNumberOfCalls(5)
//                .waitDurationInOpenState(Duration.ofMillis(20000))
//                .build();
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//    }

}
