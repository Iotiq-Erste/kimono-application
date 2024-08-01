package com.iotiq.application.config;

import com.iotiq.application.config.converter.JwtKeycloakAuthConverter;
import com.iotiq.user.security.jwt.JWTConfigurer;
import com.iotiq.user.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtKeycloakAuthConverter jwtKeycloakAuthConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/swagger-ui/*",
                                "/v3/api-docs",
                                "/v3/api-docs/*",
                                "/actuator/**")
                        .permitAll()
                        .requestMatchers("/api/v1/auth/*")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtKeycloakAuthConverter)));
        return http.build();
    }
}
