package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

public class ConstraintException extends ApplicationException {

    public <T> ConstraintException(Set<ConstraintViolation<T>>  violations) {
        super(HttpStatus.BAD_REQUEST, "constraintException", violations.stream().map(violation -> violation.getPropertyPath() + " " + violation.getMessage()).collect(Collectors.toList()));
    }

}
