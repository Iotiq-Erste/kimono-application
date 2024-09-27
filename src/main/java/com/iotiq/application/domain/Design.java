package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.DesignAppearance;
import com.iotiq.application.domain.enums.DesignColor;
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
//Design
public class Design {

    //Designanmutung
    @Enumerated(EnumType.STRING)
    private DesignAppearance designAppearance;

    //Farbe
    @Enumerated(EnumType.STRING)
    private DesignColor designColor;

}
