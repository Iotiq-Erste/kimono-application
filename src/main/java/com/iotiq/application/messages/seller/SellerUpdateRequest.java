package com.iotiq.application.messages.seller;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SellerUpdateRequest {

    private Set<Skill> skills;
    private Capacity capacity;
    private Set<ApplicationArea> applicationAreas;
}
