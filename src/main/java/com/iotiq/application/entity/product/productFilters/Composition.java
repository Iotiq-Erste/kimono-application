package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.CertificationEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.*;
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
//Zusammensetzung
public class Composition {

    //Wirkstofflache
    @ElementCollection(targetClass = ActiveSubstanceAreaEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<ActiveSubstanceAreaEnum> activeSubstanceAreaEnum;

    //Wirkstoff
    @ElementCollection(targetClass = ActiveSubstanceEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<ActiveSubstanceEnum> activeSubstanceEnum;

    //Wirkstoffplatzierung
    @ElementCollection(targetClass = ActiveSubstancePlacementEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<ActiveSubstancePlacementEnum> activeSubstancePlacementEnum;

    //Wirkstoffabgabe
    @ElementCollection(targetClass = ActiveSubstanceReleaseEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<ActiveSubstanceReleaseEnum> activeSubstanceReleaseEnum;

    //Zusammensetzung
    @ElementCollection(targetClass = CompositionEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<CompositionEnum> compositionEnum;

    //Staffelung
    @ElementCollection(targetClass = StaggeringEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<StaggeringEnum> staggeringEnum;
}
