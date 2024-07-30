package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignAppearanceEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignColorEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Design
public class Design {

    //Designanmutung
    @Enumerated(EnumType.STRING)
    private DesignAppearanceEnum designAppearanceEnum;

    //Farbe
    @Enumerated(EnumType.STRING)
    private DesignColorEnum designColorEnum;

}
