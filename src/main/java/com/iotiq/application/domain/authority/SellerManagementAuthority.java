package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("SellerManagementAuth")
public class SellerManagementAuthority {

    private SellerManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("SELLER_MANAGEMENT_VIEW");
    public static final GrantedAuthority UPDATE = new SimpleGrantedAuthority("SELLER_MANAGEMENT_UPDATE");

}
