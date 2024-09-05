package com.iotiq.application.messages.order;

import com.iotiq.application.domain.Cart;
import com.iotiq.application.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderDto {

    private String userId;
    private String orderNumber;
    private BigDecimal cargoPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private String deliveryType;
    private String deliveryAddressType;
    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;
    private Date orderDate;
    private Cart cart;
}
