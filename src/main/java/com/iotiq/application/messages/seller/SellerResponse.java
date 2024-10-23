package com.iotiq.application.messages.seller;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SellerResponse {

    private Set<Skill> skills;
    private Capacity capacity;
    private Set<ApplicationArea> applicationAreas;
    private List<OrderedProductDto> orderedProducts;
}
