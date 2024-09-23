package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.messages.customer.CustomerResponse;
import com.iotiq.application.messages.customer.CustomerUpdateRequest;
import com.iotiq.application.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    final private CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.VIEW)")
    public CustomerResponse getCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        return ModelMapperUtil.map(customer, CustomerResponse.class);
    }

    @PutMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.UPDATE)")
    public void update(@RequestBody @Valid CustomerUpdateRequest request) {
        customerService.update(request);
    }
}
