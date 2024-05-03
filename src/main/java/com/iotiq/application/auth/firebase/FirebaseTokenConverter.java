package com.iotiq.application.auth.firebase;

import com.iotiq.application.domain.Role;
import com.iotiq.user.messages.request.UserCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static com.iotiq.user.security.jwt.JwtParser.extractClaim;

public class FirebaseTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        try {
            UserCreateDto userDetails = extractClaims(source);

//            TransientUser principal = new TransientUser(UUID.randomUUID(), userDetails.getUsername(), "", userDetails.getRole().getAuthorities());
//            List<GrantedAuthority> authorities = userDetails.getRole().getAuthorities();
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, source, authorities);
//            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new JwtAuthenticationToken(source, userDetails.getRole().getAuthorities());
        } catch (Exception e) {
            throw e;
        }
    }

    private UserCreateDto extractClaims(Jwt source) {
        UserCreateDto dto = new UserCreateDto();
        dto.setUsername(extractClaim(source, "email"));
        dto.setRole(Role.ADMIN);
        return dto;
    }
}
