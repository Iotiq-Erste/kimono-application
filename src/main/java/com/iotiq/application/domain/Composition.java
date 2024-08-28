package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.ActiveSubstance;
import com.iotiq.application.domain.enums.ActiveSubstanceArea;
import com.iotiq.application.domain.enums.ActiveSubstancePlacement;
import com.iotiq.application.domain.enums.ActiveSubstanceRelease;
import com.iotiq.application.domain.enums.Staggering;
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
    @ElementCollection(targetClass = ActiveSubstanceArea.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstanceArea> activeSubstanceAreas;

    //Wirkstoff
    @ElementCollection(targetClass = ActiveSubstance.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstance> activeSubstances;

    //Wirkstoffplatzierung
    @ElementCollection(targetClass = ActiveSubstancePlacement.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstancePlacement> activeSubstancePlacements;

    //Wirkstoffabgabe
    @ElementCollection(targetClass = ActiveSubstanceRelease.class)
    @Enumerated(EnumType.STRING)
    private List<ActiveSubstanceRelease> activeSubstanceReleases;

    //Zusammensetzung
    @ElementCollection(targetClass = com.iotiq.application.domain.enums.Composition.class)
    @Enumerated(EnumType.STRING)
    private List<com.iotiq.application.domain.enums.Composition> compositions;

    //Staffelung
    @ElementCollection(targetClass = Staggering.class)
    @Enumerated(EnumType.STRING)
    private List<Staggering> staggerings;
}
