package com.iotiq.application.exception;

import com.iotiq.commons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UnremovableSkillException extends ApplicationException {
    public UnremovableSkillException() {
        super(HttpStatus.NOT_ACCEPTABLE, "unremovableSkill", List.of(), new Object[]{});
    }
}
