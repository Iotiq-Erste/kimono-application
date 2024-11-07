package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderDto;
import com.iotiq.application.messages.order.OrderResponse;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.messages.product.ProductResponse;
import com.iotiq.application.service.CustomerService;
import com.iotiq.application.service.OrderService;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    private final CustomerService customerService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.VIEW)")
    public OrderResponse getOne(@PathVariable UUID id) {
        Order order = orderService.getOrderForCurrentCustomer(id, customerService.getCurrentCustomer());
        return ModelMapperUtil.map(order, OrderResponse.class);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@OrderManagementAuth.VIEW)")
    public PagedResponse<OrderResponse> getOrders(PageableRequest pageable, Sort sort) {
        Page<OrderDto> page = orderService.getOrdersForCurrentCustomer(pageable, sort, customerService.getCurrentCustomer());

        List<OrderResponse> responseList = ModelMapperUtil.map(page.getContent(), OrderResponse.class);

        return PagedResponseBuilder.createResponse(page, responseList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(@OrderManagementAuth.CREATE)")
    public OrderCreateResponse createOrder(@RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request, customerService.getCurrentCustomer());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.DELETE)")
    public void invisible(@PathVariable("id") UUID id) {
        orderService.invisible(id, customerService.getCurrentCustomer());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@OrderManagementAuth.UPDATE)")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid OrderUpdateRequest request) {
        orderService.update(id, request, customerService.getCurrentCustomer());
    }

}
