package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.OekotexStandardEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//öko tex standard
public class OekotexStandard {

    //Öko-Tex Standard
    @Enumerated(EnumType.STRING)
    private OekotexStandardEnum oekotexStandardEnum;
}
