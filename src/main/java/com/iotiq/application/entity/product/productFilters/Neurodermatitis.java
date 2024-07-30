package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.NeurdermatitisEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Neurodermitisklasse
public class Neurodermatitis {

    //Neurodermitisklasse
    @Enumerated(EnumType.STRING)
    private NeurdermatitisEnum neurdermatitisEnum;
}
