package com.iotiq.application.domain;

import com.iotiq.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("keycloakUser")
public class KeycloakUser extends User {

    @Column(unique = true)
    private String keycloakId;

    public String getKeycloakId() {
        return this.keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }
}
