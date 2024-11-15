package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.Currency;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Price {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "{exception.greaterThan}")
    @Digits(integer = 10, fraction = 2, message = "{exception.priceAmountFormat}")
    private BigDecimal amount;
    @NotNull
    Currency currency;
}
