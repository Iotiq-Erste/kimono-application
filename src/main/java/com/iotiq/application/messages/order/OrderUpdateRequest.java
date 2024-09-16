package com.iotiq.application.messages.order;

import com.iotiq.application.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequest {

    private String deliveryAddressType;
    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;
}
