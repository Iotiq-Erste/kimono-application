package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("CartManagementAuth")
public class CartManagementAuthority {
    private CartManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("CART_MANAGEMENT_VIEW");
    public static final GrantedAuthority UPDATE = new SimpleGrantedAuthority("CART_MANAGEMENT_UPDATE");
}

