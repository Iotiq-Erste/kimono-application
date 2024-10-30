package com.iotiq.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tenant {
    public static final String ENTITY_NAME = "tenant";
    private String name;
    private String datasourceUrl;
    private String datasourceUsername;
    private String datasourcePassword;
    private String datasourceDriverClassName;
}
