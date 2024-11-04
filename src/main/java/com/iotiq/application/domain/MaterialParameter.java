package com.iotiq.application.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MaterialParameter {
    private Float thickness;
    private Integer flexibility;
    private Integer breathability;
    private Integer moistureWicking;
}
