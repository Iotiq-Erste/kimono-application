package com.iotiq.application.messages.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartCreateRequest {

    private UUID customerId;

}
