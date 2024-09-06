package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("OrderManagementAuth")
public class OrderManagementAuthority {
    private OrderManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("USER_MANAGEMENT_VIEW");
    public static final GrantedAuthority CREATE = new SimpleGrantedAuthority("USER_MANAGEMENT_CREATE");
    public static final GrantedAuthority UPDATE = new SimpleGrantedAuthority("USER_MANAGEMENT_UPDATE");
    public static final GrantedAuthority DELETE = new SimpleGrantedAuthority("USER_MANAGEMENT_DELETE");
}
