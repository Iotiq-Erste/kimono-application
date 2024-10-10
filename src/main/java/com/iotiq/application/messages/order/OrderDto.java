package com.iotiq.application.messages.order;

import com.iotiq.application.domain.enums.DeliveryStatus;
import com.iotiq.application.messages.cart.CartDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {

    private UUID id;
    private String customerId;
    private String orderNumber;
    private BigDecimal cargoPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private String deliveryType;
    private String deliveryAddressType;
    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime orderDate;
    private CartDto cart;
    private Boolean isVisible;
}
