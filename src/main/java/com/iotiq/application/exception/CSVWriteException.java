package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVWriteException extends ApplicationException {

    public CSVWriteException(Throwable e, Object... args) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "CSVUnprocessableEntity", args);
        log.error("Error occurred while writing the CSV file: {}", e.getMessage());
    }
}
