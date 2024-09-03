package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.AdultAgeGroup;
import com.iotiq.application.domain.enums.ChildrenAgeGroup;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Altersgruppen
public class AgeGroup {

    //Erwachsene
    @Enumerated(EnumType.STRING)
    private AdultAgeGroup adultAgeGroup;

    //Kinder
    @Enumerated(EnumType.STRING)
    private ChildrenAgeGroup childrenAgeGroup;
}
