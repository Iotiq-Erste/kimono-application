package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.ageGroupEnums.AdultEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.ageGroupEnums.ChildrenEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Altersgruppen
public class AgeGroup {

    //Erwachsene
    @Enumerated(EnumType.STRING)
    private AdultEnum adultEnum;

    //Kinder
    @Enumerated(EnumType.STRING)
    private ChildrenEnum childrenEnum;
}
