package com.example.demo.config;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "default_tenant";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenantId = TenantContext.getCurrentTenant(); // Utilisez une classe de contexte pour obtenir l'ID du locataire actuel
        if (currentTenantId != null) {
            return currentTenantId;
        }
        return DEFAULT_TENANT_ID; // Retourne un ID de locataire par défaut si aucun ID n'est défini
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}