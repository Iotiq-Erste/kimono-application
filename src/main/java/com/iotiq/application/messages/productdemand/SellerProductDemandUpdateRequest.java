package com.iotiq.application.messages.productdemand;

import com.iotiq.application.domain.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerProductDemandUpdateRequest {
    private RequestStatus status;
}
