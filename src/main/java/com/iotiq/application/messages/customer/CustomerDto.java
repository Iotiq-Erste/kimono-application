package com.iotiq.application.messages.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iotiq.application.domain.MedicalData;
import com.iotiq.application.domain.SizeInfo;
import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.messages.customer.contact.ContactInfo;
import com.iotiq.application.messages.order.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private ContactInfo contactInfo;

    private MedicalData medicalData;

    private SizeInfo sizeInfo;

    private CartDto cart;

    private List<OrderDto> orders;

    private boolean isActive;

}
