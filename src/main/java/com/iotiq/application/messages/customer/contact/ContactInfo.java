package com.iotiq.application.messages.customer.contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfo {

    private BasicInfo basicInfo;
    private Address address;

}
