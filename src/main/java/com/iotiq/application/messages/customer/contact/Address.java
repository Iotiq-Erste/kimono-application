package com.iotiq.application.messages.customer.contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String city;
    private String state;
    private String street;
    private String zipCode;
    private String country;
}
