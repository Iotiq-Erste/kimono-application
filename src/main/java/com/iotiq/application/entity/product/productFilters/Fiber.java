package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.CertificationEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.FiberEnum;
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
//Faser
public class Fiber {

    //Faser
    @ElementCollection(targetClass = FiberEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<FiberEnum> fiberEnum;
}
