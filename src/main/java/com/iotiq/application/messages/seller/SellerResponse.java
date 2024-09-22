package com.iotiq.application.messages.seller;

import com.iotiq.user.messages.response.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerResponse {

    private UserDto user;
    private String shopName;
    private String taxNumber;
    private boolean isActive;
}
