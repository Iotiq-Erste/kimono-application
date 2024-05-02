package com.iotiq.application.auth.keycloak;

import com.iotiq.user.security.AuthStrategy;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "auth.strategy", havingValue = "keycloak")
public class KeycloakAuthStrategy implements AuthStrategy {
    private static final Logger log = LoggerFactory.getLogger(KeycloakAuthStrategy.class);

    private final JwtKeycloakAuthConverter jwtKeycloakAuthConverter;

    @Override
    public void apply(@NotNull HttpSecurity httpSecurity) throws Exception {
        log.info("Setting auth strategy to Keycloak");
        httpSecurity
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtKeycloakAuthConverter)));
    }
}
