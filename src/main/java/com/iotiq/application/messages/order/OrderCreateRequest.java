package com.iotiq.application.messages.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderCreateRequest {

    private BigDecimal cargoPrice;
    private String deliveryType;
    private String deliveryAddressType;
    private String deliveryAddress;

}
