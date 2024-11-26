package com.iotiq.application.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotBlankIfNotNullValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankIfNotNull {
    String message() default "Value cannot be empty if provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}