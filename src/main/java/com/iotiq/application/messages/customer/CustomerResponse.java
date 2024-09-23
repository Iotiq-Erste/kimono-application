package com.iotiq.application.messages.customer;

import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.messages.order.OrderDto;
import com.iotiq.user.messages.response.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponse {

    private UserDto user;

    private CartDto cart;

    private List<OrderDto> orders;

    private boolean isActive;
}
