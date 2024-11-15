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
    @DecimalMin(value = "0.05", message = "{exception.greaterThan}")
    @DecimalMax(value = "2.00", message = "{exception.lessThanOrEqualTo}")
    private Float thickness;
    @Min(value = 0, message = "{exception.greaterThan}")
    @Max(value = 600, message = "{exception.lessThanOrEqualTo}")
    private Float weightPerUnitArea;
    @Min(value = 50, message = "{exception.greaterThan}")
    @Max(value = 500, message = "{exception.lessThanOrEqualTo}")
    private Integer breathability;
    @Min(value = 0, message = "{exception.greaterThan}")
    @Max(value = 100, message = "{exception.lessThanOrEqualTo}")
    private Integer moistureWicking;
}
