package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.CertificationEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.SpecificFunctionalityEnum;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Embeddable
//Spezifische Funktionalität
public class SpecificFunctionality {

    //Spezifische Funktionalität
    @ElementCollection(targetClass = SpecificFunctionalityEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<SpecificFunctionalityEnum> specificFunctionalityEnum;
}
