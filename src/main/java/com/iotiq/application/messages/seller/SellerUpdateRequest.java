package com.iotiq.application.messages.seller;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SellerUpdateRequest {
    private String shopName;
    private String taxNumber;
    private List<Skill> skills;
    private Capacity capacity;
    private List<ApplicationArea> applicationAreas;
}
