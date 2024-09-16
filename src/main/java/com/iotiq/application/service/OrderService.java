package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.CartItem;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Product;
import com.iotiq.application.exception.orderexceptions.OrderNotFoundException;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final SellerService sellerService;

    public Order getOrder(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    public List<Order> getOrders() {
        return customerService.getCurrentCustomer().getOrders();
    }

    public List<Order> getOrdersBySeller() {
        return orderRepository.findAllByOrderedProductsSellerId(sellerService.getCurrentSeller().getId());
    }

    public OrderCreateResponse createOrder(OrderCreateRequest createRequest) {
        Order order =  ModelMapperUtil.map(createRequest, Order.class);
        Order finalOrder = order;
        order.setOrderedProducts(createRequest.getCart().getCartItems()
                .stream().map(cartItem -> convertToOrderedProduct(cartItem, finalOrder)).collect(Collectors.toList()));
        order.setCustomer(customerService.getCurrentCustomer());

        order = orderRepository.save(order);
        return ModelMapperUtil.map(order, OrderCreateResponse.class);
    }

    public void visible(UUID id) {
        Order order = getOrder(id);
        order.setVisible(false);
        orderRepository.save(order);
    }

    public void update(UUID id, OrderUpdateRequest request) {
        Order order = getOrder(id);
        ModelMapperUtil.map(request, order);
        orderRepository.save(order);
    }

    private OrderedProduct convertToOrderedProduct(CartItem cartItem, Order order) {
        Product product = cartItem.getProduct();
        return new OrderedProduct(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getImageUrl(),
                product.getSeller().getShopName(),
                product.getSeller().getId(),
                order
        );
    }
}
