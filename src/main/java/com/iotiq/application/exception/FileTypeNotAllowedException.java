package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

public class FileTypeNotAllowedException extends ApplicationException {
    public FileTypeNotAllowedException(Object... args) {
        super(HttpStatus.BAD_REQUEST, "fileTypeNotAllowed", args);
    }
}
