package com.iotiq.application.messages.cart;

import com.iotiq.application.messages.cartitem.CartItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartUpdateRequest {
    private List<CartItemDto> cartItems;
}
