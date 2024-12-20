package com.iotiq.application.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @DecimalMin("0.05")
    @DecimalMax("2.00")
    private Float thickness;
    @Min(0)
    @Max(600)
    private Float weightPerUnitArea;
    @Min(50)
    @Max(500)
    private Integer breathability;
    @Min(0)
    @Max(100)
    private Integer moistureWicking;
}
