package com.iotiq.application.messages.productdemand;

import com.iotiq.application.domain.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SellerProductDemandUpdateRequest {
    private UUID sellerID;
    private RequestStatus requestStatus;
}
