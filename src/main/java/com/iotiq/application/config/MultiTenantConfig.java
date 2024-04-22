package com.iotiq.application.config;

import com.iotiq.application.domain.Tenant;
import com.iotiq.application.util.TenantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class MultiTenantConfig {

    @Value("${defaultTenant}")
    private String defaultTenant;

    private final TenantUtil tenantUtil;

    public MultiTenantConfig(TenantUtil tenantUtil) {
        this.tenantUtil = tenantUtil;
    }

    @Bean
    @ConfigurationProperties(prefix = "tenants")
    public DataSource dataSource() {
        List<Tenant> tenants = tenantUtil.getTenants();
        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (Tenant tenant : tenants) {
            resolvedDataSources.put(tenant.getName(), resolveDataSourceForTenant(tenant));
            log.info("DataSource with url {} for tenant {} was created.", tenant.getDatasourceUrl(), tenant.getName());
        }

        // Do the Flyway migration for all the tenant databases
//        DbMigrator.getInstance().migrateDataSources(resolvedDataSources.values());

        AbstractRoutingDataSource dataSource = new MultiTenantDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenant));
        dataSource.setTargetDataSources(resolvedDataSources);

        dataSource.afterPropertiesSet();
        return dataSource;
    }

    private DataSource resolveDataSourceForTenant(Tenant tenant) {
        createTenantDbIfNotExists(tenant);

        return DataSourceBuilder.create()
                .driverClassName(tenant.getDatasourceDriverClassName())
                .username(tenant.getDatasourceUsername())
                .password(tenant.getDatasourcePassword())
                .url(tenant.getDatasourceUrl())
                .build();
    }

    private void createTenantDbIfNotExists(Tenant tenant) {
        Connection connection = null;
        Statement statement = null;
        // dataSourceUrl (jdbc:postgresql://localhost:5432/tenant1) should be split into two values.
        // dbInstanceConnUrl: jdbc:postgresql://localhost:5432/
        // dbName: tenant1
        String dataSourceUrl = tenant.getDatasourceUrl();
        String dbInstanceConnUrl = dataSourceUrl.substring(0, dataSourceUrl.lastIndexOf('/') + 1);
        String dbName = dataSourceUrl.substring(dataSourceUrl.lastIndexOf('/') + 1);
        try {
            log.debug("Creating database if not exist...");
            connection = DriverManager.getConnection(dbInstanceConnUrl, tenant.getDatasourceUsername(), tenant.getDatasourcePassword());
            statement = connection.createStatement();
            statement.executeQuery(String.format("SELECT count(*) FROM pg_database WHERE datname = '%s'", dbName));
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count <= 0) {
                log.debug(String.format("CREATE DATABASE %s", dbName));
                statement.executeUpdate(String.format("CREATE DATABASE %s", dbName));
                log.debug("Database created.");
            } else {
                log.debug("Database already exist.");
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.debug("Closed Statement.");
                }
                if (connection != null) {
                    log.debug("Closed Connection.");
                    connection.close();
                }
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }
}