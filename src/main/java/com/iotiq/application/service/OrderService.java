package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Product;
import com.iotiq.application.exception.PricesNotMatchException;
import com.iotiq.application.messages.cartitem.CartItemDto;
import com.iotiq.application.messages.order.OrderCreateRequest;
import com.iotiq.application.messages.order.OrderCreateResponse;
import com.iotiq.application.messages.order.OrderDto;
import com.iotiq.application.messages.order.OrderUpdateRequest;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.repository.OrderRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import com.iotiq.commons.message.request.PageableRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public Order getOrderForCurrentCustomer(UUID id, Customer customer) {
        return orderRepository.findByIdAndCustomer(id, customer)
                .orElseThrow(() -> new EntityNotFoundException(Order.ENTITY_NAME, id));
    }

    public Page<OrderDto> getOrdersForCurrentCustomer(PageableRequest pageableRequest, Sort sort, Customer customer) {

        Page<Order> orderPage = orderRepository.findAllByCustomerAndIsVisibleTrue(customer, pageableRequest.buildPageable(sort));

        return orderPage.map(order -> ModelMapperUtil.map(order, OrderDto.class));
    }

    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest createRequest, Customer customer) {
        Order order = ModelMapperUtil.map(createRequest, Order.class);

        Order finalOrder = order;
        order.setOrderedProducts(createRequest.getCartItems()
                .stream().map(cartItem -> convertToOrderedProduct(cartItem, finalOrder)).collect(Collectors.toList()));
        order.setCustomer(customer);
        order.setTotalPrice(calculateTotalAmount(order.getOrderedProducts()));
        order.setOrderUtcDate(LocalDateTime.now(ZoneOffset.UTC));
        order.setOrderDate(LocalDateTime.now());

        if(createRequest.getCartTotalPrice().compareTo(order.getTotalPrice()) != 0) {
            throw new PricesNotMatchException(createRequest.getCartTotalPrice(), order.getTotalPrice());
        }

        order = orderRepository.save(order);
        return new OrderCreateResponse(order.getId(), order.getOrderNumber());
    }

    @Transactional
    public void invisible(UUID id, Customer customer) {
        Order order = getOrderForCurrentCustomer(id, customer);
        order.setVisible(false);
        orderRepository.save(order);
    }

    public void update(UUID id, OrderUpdateRequest request, Customer customer) {
        Order order = getOrderForCurrentCustomer(id, customer);
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
                product.getSeller(),
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
