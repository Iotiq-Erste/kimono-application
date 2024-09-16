package com.iotiq.application.messages.customer;

import com.iotiq.application.domain.Cart;
import com.iotiq.application.domain.Order;
import com.iotiq.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private User user;

    private Cart cart;

    private List<Order> orders;

    private boolean isActive;
}
