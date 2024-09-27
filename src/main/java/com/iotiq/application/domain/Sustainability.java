package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.EnvironmentalCompatibility;
import com.iotiq.application.domain.enums.LifeCycle;
import com.iotiq.application.domain.enums.Regionality;
import com.iotiq.application.domain.enums.ResourceConsumption;
import com.iotiq.application.domain.enums.SocialEthics;
import com.iotiq.application.domain.enums.SustainabilityComposition;
import com.iotiq.application.domain.enums.SustainabilityLightweight;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Embeddable
//Nachhaltigkeit
@AllArgsConstructor
@NoArgsConstructor
public class Sustainability {

    //Umweltvertraglichkeit
    @ElementCollection(targetClass = EnvironmentalCompatibility.class)
    @Enumerated(EnumType.STRING)
    private List<EnvironmentalCompatibility> environmentalCompatibilities;

    //Lebenszyklus
    @ElementCollection(targetClass = LifeCycle.class)
    @Enumerated(EnumType.STRING)
    private List<LifeCycle> lifeCycles;

    //Regionalität
    @ElementCollection(targetClass = Regionality.class)
    @Enumerated(EnumType.STRING)
    private List<Regionality> regionalityList;

    //Ressourcenverbrauch
    @ElementCollection(targetClass = ResourceConsumption.class)
    @Enumerated(EnumType.STRING)
    private List<ResourceConsumption> resourceConsumptions;

    //Sozialethik
    @ElementCollection(targetClass = SocialEthics.class)
    @Enumerated(EnumType.STRING)
    private List<SocialEthics> socialEthics;

    //Zusammensetzung
    @ElementCollection(targetClass = SustainabilityComposition.class)
    @Enumerated(EnumType.STRING)
    private List<SustainabilityComposition> sustainabilityCompositions;

    //Lightweight
    @ElementCollection(targetClass = SustainabilityLightweight.class)
    @Enumerated(EnumType.STRING)
    private List<SustainabilityLightweight> sustainabilityLightweights;
}
