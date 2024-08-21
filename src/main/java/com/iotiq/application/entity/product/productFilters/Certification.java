package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.CertificationEnum;
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
//Zertifizierung
public class Certification {

    //Zertifizierung
    @ElementCollection(targetClass = CertificationEnum.class)
    @Enumerated(EnumType.STRING)
    private List<CertificationEnum> certificationEnum;
}
