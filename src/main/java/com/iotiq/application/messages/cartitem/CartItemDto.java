package com.iotiq.application.messages.cartitem;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartItemDto {
    private UUID productId;
    private Long quantity;
}
