package com.iotiq.application.messages.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartUpdateRequest {
    private List<CartItemDto> cartItems;
}
