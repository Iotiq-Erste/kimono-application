package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.ApplicationAreaEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.FrequencyEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Anwendungsgebiet
public class ApplicationArea {

    //Anwendungsgebiet
    @Enumerated(EnumType.STRING)
    private ApplicationAreaEnum applicationAreaEnum;

    //Häufigkeit
    @Enumerated(EnumType.STRING)
    private FrequencyEnum frequencyEnum;
}
