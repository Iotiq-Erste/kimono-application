package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.GenderEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
// Geschlecht
public class Gender {

    // Geschlecht
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;
}
