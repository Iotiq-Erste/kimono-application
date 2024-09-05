package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.exception.orderexceptions.OrderNotFoundException;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getOrder(UUID id) {
        return orderRepository.findById(id) .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    public List<Order> getOrdersByUser(UUID userId){
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getOrdersBySupplier(UUID supplierId){
        return orderRepository.findAllByCartProductsSupplierId(supplierId);
    }

    public OrderCreateResponse createOrder(OrderCreateRequest createRequest){
        Order order = ModelMapperUtil.map(createRequest, Order.class);
        order = orderRepository.save(order);
        return ModelMapperUtil.map(order, OrderCreateResponse.class);
    }


    public void delete (UUID id){
        orderRepository.deleteById(id);
    }


    public void update(UUID id, OrderUpdateRequest request) {
        Order order = getOrder(id);
        ModelMapperUtil.map(request, order);
        orderRepository.save(order);
    }
}
