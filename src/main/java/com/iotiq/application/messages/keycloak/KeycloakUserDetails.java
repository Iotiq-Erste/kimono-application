package com.iotiq.application.messages.keycloak;

import com.iotiq.user.messages.request.UserCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeycloakUserDetails extends UserCreateDto {
    private String keycloakId;
}
