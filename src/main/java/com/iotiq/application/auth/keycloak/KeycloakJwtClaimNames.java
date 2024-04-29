package com.iotiq.application.auth.keycloak;

public final class KeycloakJwtClaimNames {
    public static final String ID = "sub";
    public static final String USERNAME = "preferred_username";
    public static final String FIRST_NAME = "given_name";
    public static final String LAST_NAME = "family_name";
    public static final String EMAIL = "email";
    public static final String REALM_ACCESS = "realm_access";

    private KeycloakJwtClaimNames() {
    }
}
