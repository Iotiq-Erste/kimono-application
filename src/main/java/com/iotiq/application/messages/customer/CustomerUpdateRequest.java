package com.iotiq.application.messages.customer;

import com.iotiq.application.domain.MedicalData;
import com.iotiq.application.domain.SizeInfo;
import com.iotiq.application.messages.customer.contact.ContactInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateRequest {
    private ContactInfo contactInfo;
    private MedicalData medicalData;
    private SizeInfo sizeInfo;
}
