package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderResponse;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.VIEW)")
    public OrderResponse getOne(@PathVariable UUID id) {
        Order order = orderService.getOrderForCurrentCustomer(id);
        return ModelMapperUtil.map(order, OrderResponse.class);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@OrderManagementAuth.VIEW)")
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderService.getOrdersForCurrentCustomer();
        return ModelMapperUtil.map(orders, OrderResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(@OrderManagementAuth.CREATE)")
    public OrderCreateResponse createOrder(@RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.DELETE)")
    public void invisible(@PathVariable("id") UUID id) {
        orderService.invisible(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.UPDATE)")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid OrderUpdateRequest request) {
        orderService.update(id, request);
    }

}
