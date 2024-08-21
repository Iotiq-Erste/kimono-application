package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.ActiveSubstanceAreaEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.ActiveSubstanceEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.ActiveSubstancePlacementEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.ActiveSubstanceReleaseEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.CompositionEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.StaggeringEnum;
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
//Zusammensetzung
public class Composition {

    //Wirkstofflache
    @ElementCollection(targetClass = ActiveSubstanceAreaEnum.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstanceAreaEnum> activeSubstanceAreaEnum;

    //Wirkstoff
    @ElementCollection(targetClass = ActiveSubstanceEnum.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstanceEnum> activeSubstanceEnum;

    //Wirkstoffplatzierung
    @ElementCollection(targetClass = ActiveSubstancePlacementEnum.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstancePlacementEnum> activeSubstancePlacementEnum;

    //Wirkstoffabgabe
    @ElementCollection(targetClass = ActiveSubstanceReleaseEnum.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstanceReleaseEnum> activeSubstanceReleaseEnum;

    //Zusammensetzung
    @ElementCollection(targetClass = CompositionEnum.class)
    @Enumerated(EnumType.STRING)
    private List<CompositionEnum> compositionEnum;

    //Staffelung
    @ElementCollection(targetClass = StaggeringEnum.class)
    @Enumerated(EnumType.STRING)
    private List<StaggeringEnum> staggeringEnum;
}
