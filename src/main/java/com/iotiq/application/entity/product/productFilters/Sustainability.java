package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.*;
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
//Nachhaltigkeit
public class Sustainability {

    //Umweltvertraglichkeit
    @ElementCollection(targetClass = EnvironmentalCompatibilityEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<EnvironmentalCompatibilityEnum> environmentalCompatibilityEnum;

    //Lebenszyklus
    @ElementCollection(targetClass = LifeCycleEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<LifeCycleEnum> lifeCycleEnum;

    //Regionalität
    @ElementCollection(targetClass = RegionalityEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<RegionalityEnum> regionalityEnum;

    //Ressourcenverbrauch
    @ElementCollection(targetClass = ResourceConsumptionEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<ResourceConsumptionEnum> resourceConsumptionEnum;

    //Sozialethik
    @ElementCollection(targetClass = SocialEthicsEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<SocialEthicsEnum> socialEthicsEnum;

    //Zusammensetzung
    @ElementCollection(targetClass = SustainabilityCompositionEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<SustainabilityCompositionEnum> sustainabilityCompositionEnum;

    //Lightweight
    @ElementCollection(targetClass = SustainabilityLightweightEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<SustainabilityLightweightEnum> sustainabilityLightweightEnum;
}
