package com.iotiq.application.messages.seller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDto {

    private String shopName;
    private String taxNumber;
    private boolean isActive;
}
