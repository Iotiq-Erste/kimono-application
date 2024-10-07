package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.AbrassionResistant;
import com.iotiq.application.domain.enums.Absorbency;
import com.iotiq.application.domain.enums.Antistatic;
import com.iotiq.application.domain.enums.Breathable;
import com.iotiq.application.domain.enums.Colorfast;
import com.iotiq.application.domain.enums.MoistureTransporting;
import com.iotiq.application.domain.enums.OdorNeutralizing;
import com.iotiq.application.domain.enums.ScratchResistant;
import com.iotiq.application.domain.enums.SweatWicking;
import com.iotiq.application.domain.enums.Washable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
//Materialverhalten
public class MaterialBehavior {

    //Scheuerbeständig
    @Enumerated(EnumType.STRING)
    private AbrassionResistant abrasionResistant;

    //Saugfähig
    @Enumerated(EnumType.STRING)
    private Absorbency absorbency;

    //Antistatisch
    @Enumerated(EnumType.STRING)
    private Antistatic antistatic;

    //Atmungsaktiv
    @Enumerated(EnumType.STRING)
    private Breathable breathable;

    //Farbecht
    @Enumerated(EnumType.STRING)
    private Colorfast colorfast;

    //Feuchtigkeitstransportierend
    @Enumerated(EnumType.STRING)
    private MoistureTransporting moistureTransporting;

    //Geruchsneutralisierend
    @Enumerated(EnumType.STRING)
    private OdorNeutralizing odorNeutralizing;

    //Kratzbeständig
    @Enumerated(EnumType.STRING)
    private ScratchResistant scratchResistant;

    //Schweißaufnehmend
    @Enumerated(EnumType.STRING)
    private SweatWicking sweatWicking;

    //Waschbar
    @Enumerated(EnumType.STRING)
    private Washable washable;
}
