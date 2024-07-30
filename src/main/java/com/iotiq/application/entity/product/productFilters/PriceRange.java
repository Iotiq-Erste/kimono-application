package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.PriceRangeEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Preisklasse
public class PriceRange {

    //Preisklasse
    @Enumerated(EnumType.STRING)
    private PriceRangeEnum priceRangeEnum;
}
