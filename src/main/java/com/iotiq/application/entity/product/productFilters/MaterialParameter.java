package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.MaterialParameterEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Material Parameter
public class MaterialParameter {

    //Material Parameter Enum
    @Enumerated(EnumType.STRING)
    private MaterialParameterEnum materialParameterEnum;
}
