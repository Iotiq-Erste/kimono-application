package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.messages.customer.CustomerCreateResponse;
import com.iotiq.application.messages.customer.CustomerDto;
import com.iotiq.application.messages.customer.CustomerUpdateRequest;
import com.iotiq.application.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    final private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreateResponse createCustomer() {
        return customerService.createCustomer();
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.VIEW)")
    public CustomerDto getCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        return ModelMapperUtil.map(customer, CustomerDto.class);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.DELETE)")
    public void delete() {
        customerService.inactiveCustomer();
    }

    @PutMapping
    @PreAuthorize("hasAuthority(@CustomerManagementAuth.UPDATE)")
    public void update(@RequestBody @Valid CustomerUpdateRequest request) {
        customerService.update(request);
    }
}
