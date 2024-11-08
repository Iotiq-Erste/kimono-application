package com.iotiq.application.config.converter;

import com.iotiq.application.domain.KeycloakUser;
import com.iotiq.application.domain.enums.Role;
import com.iotiq.application.exception.JwtConversionException;
import com.iotiq.application.messages.keycloak.KeycloakJwtClaimNames;
import com.iotiq.application.messages.keycloak.KeycloakUserDetails;
import com.iotiq.application.repository.KeycloakUserRepository;
import com.iotiq.user.domain.TransientUser;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtKeycloakAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UserRepository userRepository;
    private final KeycloakUserRepository keycloakUserRepository;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        try {
            KeycloakUserDetails userDetails = extractKeycloakUserDetails(jwt);
            if (!isValidProjectRole(userDetails.getRole())){
                return new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());
            }
            User user = handleKeycloakUser(userDetails);

            TransientUser principal = new TransientUser(user.getId(), userDetails.getUsername(), "", userDetails.getRole().getAuthorities());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, jwt, userDetails.getRole().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        }
        catch (Exception e) {
            throw new JwtConversionException(e);
        }
    }

    public User handleKeycloakUser(KeycloakUserDetails userDetails) {
        return keycloakUserRepository.findByKeycloakId(userDetails.getKeycloakId())
                .orElseGet(() -> createNewKeycloakUser(userDetails));
    }

    public boolean isValidProjectRole(com.iotiq.user.domain.authorities.Role userRole) {
        return EnumSet.allOf(Role.class).contains(userRole);
    }

    private KeycloakUser createNewKeycloakUser(KeycloakUserDetails userDetails) {
        KeycloakUser newUser = new KeycloakUser();
        newUser.setKeycloakId(userDetails.getKeycloakId());
        newUser.setUsername(userDetails.getUsername());
        newUser.getPersonalInfo().setFirstName(userDetails.getFirstname());
        newUser.getPersonalInfo().setLastName(userDetails.getLastname());
        newUser.getPersonalInfo().setEmail(userDetails.getEmail());
        newUser.setRole(userDetails.getRole());
        return userRepository.save(newUser);
    }

    private KeycloakUserDetails extractKeycloakUserDetails(Jwt jwt) {
        KeycloakUserDetails userDetails = new KeycloakUserDetails();
        userDetails.setKeycloakId(extractClaim(jwt, KeycloakJwtClaimNames.ID));
        userDetails.setUsername(extractClaim(jwt, KeycloakJwtClaimNames.USERNAME));
        userDetails.setEmail(extractClaim(jwt, KeycloakJwtClaimNames.EMAIL));
        userDetails.setRole(extractUserRole(jwt));
        return userDetails;
    }

    private static String extractClaim(Jwt jwt, String claimName) {
        return jwt.getClaim(claimName);
    }

    private static Role extractUserRole(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim(KeycloakJwtClaimNames.REALM_ACCESS);
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            return getRoleByNames(roles);
        }
        log.error("Realm_access or roles not present in the token.");
        return null;
    }

    static Role getRoleByNames(List<String> roles) {
        Set<String> userRoles = new HashSet<>(roles);
        for (Role role : Role.values()) {
            if (userRoles.contains(role.name())) {
                return role;
            }
        }
        log.error("No matching role found in realm_access roles.");
        return null;
    }
}
