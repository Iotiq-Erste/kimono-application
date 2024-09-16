package com.iotiq.application.messages.seller;

import com.iotiq.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDto {

    private User user;
    private String shopName;
    private String taxNumber;
    private boolean isActive;
}
