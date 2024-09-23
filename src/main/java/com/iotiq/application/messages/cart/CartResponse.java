package com.iotiq.application.messages.cart;

import com.iotiq.application.messages.cartitem.CartItemDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartResponse {
    private List<CartItemDetailDto> cartItems;
    private BigDecimal totalAmount;
}
