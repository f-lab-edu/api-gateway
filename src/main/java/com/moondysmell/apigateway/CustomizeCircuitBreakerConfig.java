package com.moondysmell.apigateway;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomizeCircuitBreakerConfig {

    @Value("${circuit-breaker.timeout-duration-millisecond}")
    private int TIMEOUT_DURATION;

    @Bean
    public ReactiveResilience4JCircuitBreakerFactory defaultCustomizer() {

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 슬라이딩 타입 (Time_BASED)
                .slidingWindowSize(2) // 통계건수(count-10번, time-10s)
                .minimumNumberOfCalls(5) // 서킷브레이커가 에러비율 또 slow call 비율을 계산하기 전에 요구되는 최소한의 요청 수
                .failureRateThreshold(5) // 에러비율(50%)로 해당값 이상으로 에러 발생 시 서킷이 open
                .permittedNumberOfCallsInHalfOpenState(2) // 서킷이 반 오픈상태일때 받아들일 요청 개수
               .waitDurationInOpenState(Duration.ofSeconds(5)) //서킷 상태가 open에서 half-open으로 변경되기 전에 서킷브레이커가 기다리는 시간
                .recordExceptions(Throwable.class) // 적용할 예외
                .build();

        ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
        factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(TIMEOUT_DURATION)).build())
                .circuitBreakerConfig(circuitBreakerConfig).build());

        return factory;
    }
}