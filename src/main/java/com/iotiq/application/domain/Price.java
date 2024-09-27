package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.Currency;
import jakarta.persistence.Embeddable;
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

    BigDecimal amount;

    Currency currency;
}
