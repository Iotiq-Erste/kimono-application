package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.RatingEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Bewertungen
public class Rating {

    //Bewertungen
    @Enumerated(EnumType.STRING)
    private RatingEnum ratingEnum;
}
