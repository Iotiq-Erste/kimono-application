package com.iotiq.application.auth.firebase;

import com.iotiq.user.security.AuthStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "auth.strategy", havingValue = "firebase")
public class FirebaseAuthStrategy implements AuthStrategy {
    private static final Logger log = LoggerFactory.getLogger(FirebaseAuthStrategy.class);

    @Override
    public void apply(HttpSecurity httpSecurity) throws Exception {
        log.info("Setting auth strategy to Firebase");
        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(configurer -> configurer.jwtAuthenticationConverter(new FirebaseTokenConverter())));
    }
}
