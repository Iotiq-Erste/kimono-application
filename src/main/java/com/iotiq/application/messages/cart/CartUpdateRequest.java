package com.iotiq.application.messages.cart;

import com.iotiq.application.domain.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartUpdateRequest {
    private List<CartItem> cartItems;
}
