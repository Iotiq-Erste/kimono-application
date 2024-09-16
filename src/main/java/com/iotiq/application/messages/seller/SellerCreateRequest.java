package com.iotiq.application.messages.seller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCreateRequest {
    private String shopName;
    private String taxNumber;
}
