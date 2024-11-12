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
    @DecimalMin("0.0")
    @DecimalMax("600.00")
    private Float weightPerUnitArea;
    @Min(0)
    @Max(100)
    private Integer breathability;
    @Min(0)
    @Max(100)
    private Integer moistureWicking;
}
