package com.iotiq.application.config;

import com.iotiq.commons.config.MessageResourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
@RequiredArgsConstructor
public class ValidatorConfig {

    private final MessageResourceConfig messageResourceConfig;

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageResourceConfig.messageSource());
        return bean;
    }
}
