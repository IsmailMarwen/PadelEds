package com.example.demo.config;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Map;

@Configuration
public class HibernateConfig {

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return new HibernatePropertiesCustomizer() {
            @Override
            public void customize(Map<String, Object> hibernateProperties) {
                hibernateProperties.put("hibernate.multiTenancy", "DATABASE");
                hibernateProperties.put("hibernate.tenant_identifier_resolver", tenantIdentifierResolver);
                hibernateProperties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider());
                hibernateProperties.put("hibernate.multi_tenant.datasource.identifier", "tenantId");
            }
        };
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new EdsPadelMultiTenantConnectionProvider();
    }
}
