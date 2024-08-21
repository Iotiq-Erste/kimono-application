package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.EnvironmentalCompatibilityEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.LifeCycleEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.RegionalityEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.ResourceConsumptionEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SocialEthicsEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SustainabilityCompositionEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SustainabilityLightweightEnum;
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
//Nachhaltigkeit
public class Sustainability {

    //Umweltvertraglichkeit
    @ElementCollection(targetClass = EnvironmentalCompatibilityEnum.class)
    @Enumerated(EnumType.STRING)
    private List<EnvironmentalCompatibilityEnum> environmentalCompatibilityEnum;

    //Lebenszyklus
    @ElementCollection(targetClass = LifeCycleEnum.class)
    @Enumerated(EnumType.STRING)
    private List<LifeCycleEnum> lifeCycleEnum;

    //Regionalität
    @ElementCollection(targetClass = RegionalityEnum.class)
    @Enumerated(EnumType.STRING)
    private List<RegionalityEnum> regionalityEnum;

    //Ressourcenverbrauch
    @ElementCollection(targetClass = ResourceConsumptionEnum.class)
    @Enumerated(EnumType.STRING)
    private List<ResourceConsumptionEnum> resourceConsumptionEnum;

    //Sozialethik
    @ElementCollection(targetClass = SocialEthicsEnum.class)
    @Enumerated(EnumType.STRING)
    private List<SocialEthicsEnum> socialEthicsEnum;

    //Zusammensetzung
    @ElementCollection(targetClass = SustainabilityCompositionEnum.class)
    @Enumerated(EnumType.STRING)
    private List<SustainabilityCompositionEnum> sustainabilityCompositionEnum;

    //Lightweight
    @ElementCollection(targetClass = SustainabilityLightweightEnum.class)
    @Enumerated(EnumType.STRING)
    private List<SustainabilityLightweightEnum> sustainabilityLightweightEnum;
}
