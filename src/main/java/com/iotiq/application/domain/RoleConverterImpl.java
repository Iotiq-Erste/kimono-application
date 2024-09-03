package com.iotiq.application.domain;

import com.iotiq.user.domain.authorities.Role;
import com.iotiq.user.domain.authorities.RoleConverter;
import org.springframework.stereotype.Component;

@Component("application-role-converter")
public class RoleConverterImpl implements RoleConverter {
    @Override
    public Role convert(String role) {
        return switch (role) {
            case "SUPER_ADMIN" -> com.iotiq.application.domain.enums.Role.SUPER_ADMIN;
            case "CURATOR" -> com.iotiq.application.domain.enums.Role.CURATOR;
            case "VISITOR" -> com.iotiq.application.domain.enums.Role.VISITOR;
            case "ADMIN" -> com.iotiq.application.domain.enums.Role.ADMIN;
            case "COMPANY" -> com.iotiq.application.domain.enums.Role.COMPANY;
            case "CUSTOMER" -> com.iotiq.application.domain.enums.Role.CUSTOMER;
            default -> null;
        };
    }
}
