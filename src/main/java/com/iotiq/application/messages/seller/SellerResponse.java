package com.iotiq.application.messages.seller;

import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.user.messages.response.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SellerResponse {

    private UserDto user;
    private String shopName;
    private String taxNumber;
    private boolean isActive;
    private List<Skill> skills;
    private Capacity capacity;
    private ApplicationArea applicationArea;
    private List<Product> product;
    private List<Order> orders;
}
