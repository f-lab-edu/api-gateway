package com.moondysmell.apigateway;

import com.moondysmell.apigateway.auth.JwtProperties;
import com.moondysmell.apigateway.auth.JwtUtils;
import com.moondysmell.apigateway.auth.TokenUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ApiGatewayApplicationTests {
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("moondysmellwebapplicationsecret");
        jwtProperties.setExpirationSecond(3600 * 6);

        jwtUtils = new JwtUtils(jwtProperties);
        jwtUtils.afterPropertiesSet();
    }

    @Test
    void generateJwt() {
        // given
        TokenUser tokenUser = new TokenUser("ASk", "ROLE_USER");
        String generatedToken = jwtUtils.generate(tokenUser);

        // when
        TokenUser decodedUser = jwtUtils.decode(generatedToken);

        // then
        log.info("generatedToken : {}", generatedToken);
        log.info(">>> validation: " +(decodedUser).equals(tokenUser));


    }

}
