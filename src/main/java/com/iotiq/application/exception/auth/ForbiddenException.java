package com.iotiq.application.exception.auth;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ForbiddenException extends ApplicationException {

    public ForbiddenException(String entityName, Object... args) {
        super(HttpStatus.FORBIDDEN, "forbidden", List.of(entityName), args);
    }
}
