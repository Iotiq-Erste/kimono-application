package com.iotiq.application.auth.keycloak;

import com.iotiq.user.domain.TransientUser;
import com.iotiq.user.security.AuthStrategy;
import com.iotiq.user.security.jwt.JwtParser;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.iotiq.user.security.jwt.JwtParser.extractClaim;

@Component
@ConditionalOnProperty(name = "auth.strategy", havingValue = "keycloak")
public class JwtKeycloakAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>, AuthStrategy {
    private static final Logger log = LoggerFactory.getLogger(JwtKeycloakAuthConverter.class);


    private final JwtParser jwtParser;

    public JwtKeycloakAuthConverter(JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    @Override
    public void apply(@NotNull HttpSecurity httpSecurity) throws Exception {
        log.info("Setting auth strategy to Keycloak");
        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(configurer -> configurer.jwtAuthenticationConverter(this)));
    }

    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        try {
            KeycloakUserDetails userDetails = extractKeycloakUserDetails(jwt);

            TransientUser principal = new TransientUser(UUID.randomUUID(), userDetails.getUsername(), "", userDetails.getRole().getAuthorities());
            List<GrantedAuthority> authorities = userDetails.getRole().getAuthorities();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, jwt, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        } catch (Exception e) {
            throw e;
        }
    }

    @NotNull
    private KeycloakUserDetails extractKeycloakUserDetails(Jwt jwt) {
        KeycloakUserDetails userDetails = new KeycloakUserDetails();

        userDetails.setKeycloakId(extractClaim(jwt, KeycloakJwtClaimNames.ID));
        userDetails.setUsername(extractClaim(jwt, KeycloakJwtClaimNames.USERNAME));
        userDetails.setFirstname(extractClaim(jwt, KeycloakJwtClaimNames.FIRST_NAME));
        userDetails.setLastname(extractClaim(jwt, KeycloakJwtClaimNames.LAST_NAME));
        userDetails.setEmail(extractClaim(jwt, KeycloakJwtClaimNames.EMAIL));
        userDetails.setRole(jwtParser.extractUserRole(jwt, KeycloakJwtClaimNames.REALM_ACCESS));

        return userDetails;
    }
}
