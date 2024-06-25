package com.example.demo.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
@Primary
public class EdsPadelMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final String DEFAULT_TENANT = "padel";

    @Autowired
    private Map<String, DataSource> dataSources;

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSources.get(DEFAULT_TENANT);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        DataSource dataSource = dataSources.get(tenantIdentifier);
        if (dataSource != null) {
            return dataSource;
        }
        return selectAnyDataSource();
    }
}
