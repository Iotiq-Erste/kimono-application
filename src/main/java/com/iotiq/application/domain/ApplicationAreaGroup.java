package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.UsageCycle;
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
//Anwendungsgebiet
public class ApplicationAreaGroup {

    //Anwendungsgebiet
    @Enumerated(EnumType.STRING)
    private ApplicationArea applicationArea;

    //Häufigkeit
    @Enumerated(EnumType.STRING)
    private UsageCycle usageCycle;
}
