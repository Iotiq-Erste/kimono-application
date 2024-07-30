package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Haptik
public class Haptics {

    //Elasitsch
    @Enumerated(EnumType.STRING)
    private ElasticEnum elasticEnum;

    //Fein
    @Enumerated(EnumType.STRING)
    private FineEnum fineEnum;

    //Leichtgewichtig
    @Enumerated(EnumType.STRING)
    private LightweightEnum lightweightEnum;

    //Fusselfrei
    @Enumerated(EnumType.STRING)
    private LintFreeEnum lintFreeEnum;

    //Kratzig
    @Enumerated(EnumType.STRING)
    private ScratchyEnum scratchyEnum;

    //Nahtspürbar
    @Enumerated(EnumType.STRING)
    private SeamFeelableEnum seamFeelableEnum;

    //Weich
    @Enumerated(EnumType.STRING)
    private SoftEnum softEnum;

    //Gleichmäßig
    @Enumerated(EnumType.STRING)
    private UniformEnum uniformEnum;

}
