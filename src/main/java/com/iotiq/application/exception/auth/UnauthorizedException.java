package com.iotiq.application.exception.auth;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException(String entityName, Object... args) {
        super(HttpStatus.UNAUTHORIZED, "unauthorized", List.of(entityName), args);
    }
}
