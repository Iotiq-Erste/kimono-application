package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Frequency;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Anwendungsgebiet
public class ApplicationAreaGroup {

    //Anwendungsgebiet
    @Enumerated(EnumType.STRING)
    private ApplicationArea applicationArea;

    //Häufigkeit
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
}
