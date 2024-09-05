package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderDto;
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
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public OrderDto getOne(@PathVariable UUID id) {
        Order order = orderService.getOrder(id);
        return ModelMapperUtil.map(order, OrderDto.class);
    }

    @GetMapping("/getOrdersByUser/{userId}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public List<OrderDto> getOrdersByUser(@PathVariable UUID userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ModelMapperUtil.map(orders, OrderDto.class);
    }

    @GetMapping("/getOrdersBySupplier/{supplierId}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public List<OrderDto> getOrderBySupplier(@PathVariable UUID supplierId) {
        List<Order> orders = orderService.getOrdersBySupplier(supplierId);
        return ModelMapperUtil.map(orders, OrderDto.class);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(@OrderManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreateResponse createOrder(@RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        orderService.delete(id);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid OrderUpdateRequest request) {
        orderService.update(id, request);
    }




}
