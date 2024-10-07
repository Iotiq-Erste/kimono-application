package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@Slf4j
public class JwtConversionException extends ApplicationException {
    public JwtConversionException(Throwable e) {
        super(HttpStatus.FORBIDDEN, "JwtConversionException", Collections.emptyList());

        log.error("Error converting JWT to Authentication token: {}", e.getMessage());
    }
}
