package com.iotiq.application.messages.seller;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SellerDto {

    private String shopName;

    private String taxNumber;

    private boolean isActive;

    private List<Skill> skills;

    private Capacity capacity;

    private ApplicationArea applicationArea;

    private List<OrderedProductDto> orderedProducts;
}
