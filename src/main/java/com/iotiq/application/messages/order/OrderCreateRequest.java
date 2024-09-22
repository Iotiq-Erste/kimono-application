package com.iotiq.application.messages.order;

import com.iotiq.application.messages.cartitem.CartItemDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {

    private BigDecimal cargoPrice;
    private String deliveryType;
    private String deliveryAddressType;
    private String deliveryAddress;
    private List<CartItemDto> cartItems;

}
