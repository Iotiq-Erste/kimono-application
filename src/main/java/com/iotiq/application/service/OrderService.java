package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Product;
import com.iotiq.application.exception.orderexceptions.OrderNotFoundException;
import com.iotiq.application.messages.cartitem.CartItemDto;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final SellerService sellerService;
    private final ProductService productService;

    public Order getOrderForCurrentCustomer(UUID id) {
        return orderRepository.findByIdAndCustomer(id, customerService.getCurrentCustomer())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    public List<Order> getOrdersForCurrentCustomer() {
        return customerService.getCurrentCustomer().getOrders();
    }

    public List<Order> getOrdersBySeller() {
        return orderRepository.findAllByOrderedProductsSellerId(sellerService.getCurrentSeller().getId());
    }

    public OrderCreateResponse createOrder(OrderCreateRequest createRequest) {
        Order order = ModelMapperUtil.map(createRequest, Order.class);

        Order finalOrder = order;
        order.setOrderedProducts(createRequest.getCartItems()
                .stream().map(cartItem -> convertToOrderedProduct(cartItem, finalOrder)).collect(Collectors.toList()));
        order.setCustomer(customerService.getCurrentCustomer());
        order.setTotalPrice(calculateTotalAmount(order.getOrderedProducts()));
        order.setOrderDate(LocalDate.now());

        if (createRequest.getCartTotalPrice().compareTo(order.getTotalPrice()) != 0) {
            throw new RuntimeException("Total prices did not match");
        }

        order = orderRepository.save(order);
        return new OrderCreateResponse(order.getId(), order.getOrderNumber());
    }

    public void invisible(UUID id) {
        Order order = getOrderForCurrentCustomer(id);
        order.setVisible(false);
        orderRepository.save(order);
    }

    public void update(UUID id, OrderUpdateRequest request) {
        Order order = getOrderForCurrentCustomer(id);
        ModelMapperUtil.map(request, order);
        orderRepository.save(order);
    }

    private OrderedProduct convertToOrderedProduct(CartItemDto cartItemDto, Order order) {

        Product product = productService.getOne(cartItemDto.getProductId());
        return new OrderedProduct(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getImageUrl(),
                product.getSeller().getShopName(),
                product.getSeller().getId(),
                order,
                cartItemDto.getQuantity()
        );
    }

    private BigDecimal calculateTotalAmount(List<OrderedProduct> orderedProducts) {
        return orderedProducts.stream().map(orderedProduct -> BigDecimal.valueOf(orderedProduct.getQuantity())
                        .multiply(orderedProduct.getPrice().getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
