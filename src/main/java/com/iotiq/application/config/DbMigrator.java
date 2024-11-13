package com.iotiq.application.config;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;

@Component
public class DbMigrator {

    public void migrateDataSources(Collection<Object> dataSources) {
        dataSources.forEach(
                dataSource -> {
                    Flyway tenantDbMigration = Flyway.configure()
                            .dataSource((DataSource) dataSource)
                            .outOfOrder(true)
                            .baselineOnMigrate(true)
                            .load();
                    tenantDbMigration.migrate();
                }
        );
    }
}
