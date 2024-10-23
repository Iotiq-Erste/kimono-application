package com.iotiq.application.domain.enums;

import com.iotiq.application.domain.authority.CartManagementAuthority;
import com.iotiq.application.domain.authority.CustomerManagementAuthority;
import com.iotiq.application.domain.authority.OrderManagementAuthority;
import com.iotiq.application.domain.authority.ProductDemandManagementAuthority;
import com.iotiq.application.domain.authority.OrderedProductManagementAuthority;
import com.iotiq.application.domain.authority.ProductManagementAuthority;
import com.iotiq.application.domain.authority.SellerManagementAuthority;
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
            ProductManagementAuthority.DELETE,
            OrderManagementAuthority.VIEW,
            SellerManagementAuthority.UPDATE,
            SellerManagementAuthority.CREATE,
            SellerManagementAuthority.DELETE,
            SellerManagementAuthority.VIEW,
            ProductDemandManagementAuthority.VIEW,
            OrderedProductManagementAuthority.VIEW
    ),
    CUSTOMER(
            new SimpleGrantedAuthority("ROLE_CUSTOMER"),
            ProductManagementAuthority.VIEW,
            CartManagementAuthority.VIEW,
            CartManagementAuthority.UPDATE,
            OrderManagementAuthority.CREATE,
            OrderManagementAuthority.DELETE,
            OrderManagementAuthority.VIEW,
            CustomerManagementAuthority.UPDATE,
            CustomerManagementAuthority.CREATE,
            CustomerManagementAuthority.DELETE,
            CustomerManagementAuthority.VIEW,
            ProductDemandManagementAuthority.VIEW,
            ProductDemandManagementAuthority.CREATE,
            ProductDemandManagementAuthority.UPDATE,
            ProductDemandManagementAuthority.DELETE


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
