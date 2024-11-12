package com.iotiq.application.config;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.util.Collection;

public class DbMigrator {
    private static DbMigrator instance;

    private DbMigrator() {
    }

    public static DbMigrator getInstance() {
        if (instance == null) {
            instance = new DbMigrator();
        }
        return instance;
    }

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
