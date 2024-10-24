package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("OrderedProductManagementAuth")
public class OrderedProductManagementAuthority {

    private OrderedProductManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("ORDERED_PRODUCT_MANAGEMENT_VIEW");
}

