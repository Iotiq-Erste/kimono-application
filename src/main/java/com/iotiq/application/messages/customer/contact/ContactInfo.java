package com.iotiq.application.messages.customer.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfo {

    private BasicInfo basicInfo;
    @JsonInclude
    private Address address;



}
