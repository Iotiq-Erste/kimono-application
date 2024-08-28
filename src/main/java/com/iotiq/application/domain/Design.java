package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.DesignAppearance;
import com.iotiq.application.domain.enums.DesignColor;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
//Design
public class Design {

    //Designanmutung
    @Enumerated(EnumType.STRING)
    private DesignAppearance designAppearance;

    //Farbe
    @Enumerated(EnumType.STRING)
    private DesignColor designColor;

}
