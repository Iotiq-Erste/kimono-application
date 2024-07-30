package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Materialverhalten
public class MaterialBehavior {

    //Scheuerbeständig
    @Enumerated(EnumType.STRING)
    private AbrassionResistantEnum abrasionResistantEnum;

    //Saugfähig
    @Enumerated(EnumType.STRING)
    private AbsorbentEnum absorbentEnum;

    //Antistatisch
    @Enumerated(EnumType.STRING)
    private AntistaticEnum antistaticEnum;

    //Atmungsaktiv
    @Enumerated(EnumType.STRING)
    private BreathableEnum breathableEnum;

    //Farbecht
    @Enumerated(EnumType.STRING)
    private ColorfastEnum colorfastEnum;

    //Feuchtigkeitstransportierend
    @Enumerated(EnumType.STRING)
    private MoistureTransportingEnum moistureTransportingEnum;

    //Geruchsneutralisierend
    @Enumerated(EnumType.STRING)
    private OdorNeutralizingEnum odorNeutralizingEnum;

    //Kratzbeständig
    @Enumerated(EnumType.STRING)
    private ScratchResistantEnum scratchResistantEnum;

    //Schweißaufnehmend
    @Enumerated(EnumType.STRING)
    private SweatWickingEnum sweatWickingEnum;

    //Waschbar
    @Enumerated(EnumType.STRING)
    private WashableEnum washableEnum;
}
