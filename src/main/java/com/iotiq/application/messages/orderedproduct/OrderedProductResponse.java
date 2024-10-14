package com.iotiq.application.messages.orderedproduct;

import com.iotiq.application.domain.Price;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderedProductResponse {

    private UUID productId;
    private String title;
    private Price price;
    private String imageUrl;
    private String orderNumber;
    private LocalDateTime orderDate;
    private String deliveryType;
    private String deliveryAddressType;
    private String deliveryAddress;
    private BasicInfo customerBasicInfo;
    private Long quantity;
}
