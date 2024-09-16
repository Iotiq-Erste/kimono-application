package com.iotiq.application.messages.cart;

import com.iotiq.application.domain.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartDto {

    private UUID id;

    private UUID customerId;

    private List<CartItem> cartItems;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
