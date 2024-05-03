package com.iotiq.application.auth.keycloak;

import com.iotiq.user.domain.TransientUser;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import com.iotiq.user.security.jwt.JwtParser;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.iotiq.user.security.jwt.JwtParser.extractClaim;

@Component
@RequiredArgsConstructor
public class JwtKeycloakAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final Logger log = LoggerFactory.getLogger(JwtKeycloakAuthConverter.class);
    private final JwtParser jwtParser;
    private final UserService userService;

    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
//        try {
        KeycloakUserDetails userDetails = extractUserDetails(jwt);
        TransientUser principal = getPrincipal(userDetails);

        List<GrantedAuthority> authorities = userDetails.getRole().getAuthorities();
        return new UsernamePasswordAuthenticationToken(principal, userDetails.getKeycloakId(), authorities);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            throw e;
//        }
    }

    private @NotNull TransientUser getPrincipal(KeycloakUserDetails userDetails) {
        User user = userService.loadExternalUserOrCreate(userDetails);
        return new TransientUser(user.getId(), userDetails.getUsername(), userDetails.getRole().getAuthorities());
    }

    @NotNull
    private KeycloakUserDetails extractUserDetails(Jwt jwt) {
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
