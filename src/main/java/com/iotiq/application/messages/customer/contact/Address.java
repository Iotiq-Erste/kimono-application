package com.iotiq.application.messages.customer.contact;

import com.iotiq.application.domain.enums.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String city;
    private String state;
    private String street;
    private String zipCode;
    private Country country;
}
