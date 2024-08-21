package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.SpecificBodyPartEnum;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Embeddable
//Spezifische Körperstelle
public class SpecificBodyPart {

    //Spezifische Körperstelle
    @ElementCollection(targetClass = SpecificBodyPartEnum.class)
    @Enumerated(EnumType.STRING)
    private List<SpecificBodyPartEnum> specificBodyPartEnum;
}
