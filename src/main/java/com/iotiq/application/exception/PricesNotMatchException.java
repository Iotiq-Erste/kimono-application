package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Collections;

public class PricesNotMatchException extends ApplicationException {
    public PricesNotMatchException(BigDecimal... prices) {
        super(HttpStatus.BAD_REQUEST, "pricesNotMatch", Collections.emptyList(), prices);
    }
}
