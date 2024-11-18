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
    @DecimalMin(value = "0.05")
    @DecimalMax(value = "2.00")
    private Float thickness;
    @Min(value = 0)
    @Max(value = 600)
    private Float weightPerUnitArea;
    @Min(value = 50)
    @Max(value = 500)
    private Integer breathability;
    @Min(value = 0)
    @Max(value = 100)
    private Integer moistureWicking;
}
