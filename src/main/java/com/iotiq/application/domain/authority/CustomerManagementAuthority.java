package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("CustomerManagementAuth")
public class CustomerManagementAuthority {

    private CustomerManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("CUSTOMER_MANAGEMENT_VIEW");
    public static final GrantedAuthority UPDATE = new SimpleGrantedAuthority("CUSTOMER_MANAGEMENT_UPDATE");
}
