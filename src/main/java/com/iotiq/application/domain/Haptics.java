package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.Elasticity;
import com.iotiq.application.domain.enums.Fineness;
import com.iotiq.application.domain.enums.Lightweight;
import com.iotiq.application.domain.enums.LintFree;
import com.iotiq.application.domain.enums.Scratchy;
import com.iotiq.application.domain.enums.SeamFeelable;
import com.iotiq.application.domain.enums.Softness;
import com.iotiq.application.domain.enums.Uniform;
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
    private Elasticity elasticity;

    //Fein
    @Enumerated(EnumType.STRING)
    private Fineness fineness;

    //Leichtgewichtig
    @Enumerated(EnumType.STRING)
    private Lightweight lightweight;

    //Fusselfrei
    @Enumerated(EnumType.STRING)
    private LintFree lintFree;

    //Kratzig
    @Enumerated(EnumType.STRING)
    private Scratchy scratchy;

    //Nahtspürbar
    @Enumerated(EnumType.STRING)
    private SeamFeelable seamFeelable;

    //Weich
    @Enumerated(EnumType.STRING)
    private Softness softness;

    //Gleichmäßig
    @Enumerated(EnumType.STRING)
    private Uniform uniform;

}
