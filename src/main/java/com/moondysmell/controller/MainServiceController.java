package com.moondysmell.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MainServiceController {

    private static final String MAIN_SERVICE = "mainService";

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }


//    @ResponseStatus(HttpStatus.OK)
//    @CircuitBreaker(name = MAIN_SERVICE, fallbackMethod = "fallmsg")
//    @GetMapping("/resilience")
//    public ResponseEntity<String> getCircuitBreakerTest(){
//        //CircuitBreaker circuitBreaker = circuitBreakerFactory.create(MAIN_SERVICE);
//        String url = "http://localhost:8800/spring-service/";
//        log.info("This is getCircuitbreaker method in MainServiceController");
//        String response = restTemplate.getForObject("http://localhost:8800/spring-service/", String.class);
//        return new ResponseEntity<String>(response, HttpStatus.FORBIDDEN);
//        //return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class), throwable -> fallmsg());
//    }

    @GetMapping("/fallback")
    public ResponseEntity<String> fallmsg(){
        //return "This is Fallback Service";
        return new ResponseEntity<String>("This is Fallback Service", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
