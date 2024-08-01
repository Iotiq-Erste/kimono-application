package com.iotiq.application.domain;

import com.iotiq.application.domain.authority.ProductManagementAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role implements com.iotiq.user.domain.authorities.Role {

    SUPER_ADMIN(
            new SimpleGrantedAuthority("ROLE_SUPER_ADMIN")
    ),
    ADMIN(
            new SimpleGrantedAuthority("ROLE_ADMIN")
    ),
    COMPANY(
            new SimpleGrantedAuthority("ROLE_COMPANY"),
            ProductManagementAuthority.VIEW,
            ProductManagementAuthority.CREATE,
            ProductManagementAuthority.UPDATE,
            ProductManagementAuthority.DELETE
    ),
    CUSTOMER(
            new SimpleGrantedAuthority("ROLE_CUSTOMER"),
            ProductManagementAuthority.VIEW
    ),
    CURATOR(),
    VISITOR();

    final List<GrantedAuthority> authorities;

    Role(GrantedAuthority... authorities) {
        this.authorities = List.of(authorities);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
