package com.iotiq.application.domain.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("ProductDemandManagementAuth")
public class ProductDemandManagementAuthority {

    private ProductDemandManagementAuthority() {
    }

    public static final GrantedAuthority VIEW = new SimpleGrantedAuthority("PRODUCT_DEMAND_MANAGEMENT_VIEW");
    public static final GrantedAuthority CREATE = new SimpleGrantedAuthority("PRODUCT_DEMAND_MANAGEMENT_CREATE");
    public static final GrantedAuthority UPDATE = new SimpleGrantedAuthority("PRODUCT_DEMAND_MANAGEMENT_UPDATE");
    public static final GrantedAuthority DELETE = new SimpleGrantedAuthority("PRODUCT_DEMAND_MANAGEMENT_DELETE");
}
