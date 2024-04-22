package com.iotiq.application.util;

import com.iotiq.application.domain.Tenant;
import com.iotiq.application.exception.TenantException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
@Configuration
public class TenantUtil {

    private final String tenantPropertiesPath;

    private final List<Tenant> tenants;

    public TenantUtil(
            @Value("${tenantPropertiesPath}") String tenantPropertiesPath
    ) {
        log.info("Tenant Properties Path is set to: {}", tenantPropertiesPath);
        this.tenantPropertiesPath = tenantPropertiesPath;
        this.tenants = getTenants();
    }

    public List<Tenant> getTenants() {
        if (tenants != null) {
            return tenants;
        }

        if (StringUtils.isBlank(tenantPropertiesPath)) {
            throw new TenantException("Exiting because tenant folder path is not configured");
        }

        File[] files = getFiles();
        log.info("Found tenant files: {}", Arrays.toString(files));
        List<Tenant> foundTenants = new ArrayList<>();

        for (File file : files) {
            Properties tenantProperties = new Properties();
            loadPropertiesFromFile(file, tenantProperties);

            Tenant tenant = createTenantFromProperties(tenantProperties);

            foundTenants.add(tenant);
        }
        log.info("Found Tenants set to: {}", foundTenants);
        return foundTenants;
    }

    public static Tenant createTenantFromProperties(Properties tenantProperties) {
        return Tenant.builder()
                .name(tenantProperties.getProperty("name"))
                .datasourceUsername(tenantProperties.getProperty("datasource.username"))
                .datasourcePassword(tenantProperties.getProperty("datasource.password"))
                .datasourceUrl(tenantProperties.getProperty("datasource.url"))
                .datasourceDriverClassName(tenantProperties.getProperty("datasource.driver-class-name"))
//                .keycloakInfo(
//                        new KeycloakInfo(
//                                tenantProperties.getProperty("keycloak.url"),
//                                tenantProperties.getProperty("keycloak.realm"),
//                                tenantProperties.getProperty("keycloak.clientId")
//                        )
//                )
                .build();
    }

    private static void loadPropertiesFromFile(File file, Properties tenantProperties) {
        try (FileInputStream inStream = new FileInputStream(file)) {
            tenantProperties.load(inStream);
        } catch (IOException e) {
            throw new TenantException("Tenant file is malformed: " + file.getName());
        }
    }

    private File[] getFiles() {
        try {
            File folder = new ClassPathResource(tenantPropertiesPath).getFile();
            return folder.listFiles();
        } catch (IOException e) {
            throw new TenantException(
                    "Exiting because tenant folder is missing. Put the tenant folder and files on the classpath. Classpath is " +
                            tenantPropertiesPath
            );
        }
    }
}
