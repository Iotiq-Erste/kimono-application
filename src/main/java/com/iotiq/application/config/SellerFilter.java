package com.iotiq.application.config;

import com.iotiq.application.domain.enums.Role;
import com.iotiq.application.service.SellerService;
import com.iotiq.user.internal.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
public class SellerFilter implements Filter {

    private final SellerService sellerService;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().equals(Role.COMPANY.getAuthorities())) {
            sellerService.createIfNotExists(userService.getCurrentUser());
        }
        chain.doFilter(request, response);
    }
}
