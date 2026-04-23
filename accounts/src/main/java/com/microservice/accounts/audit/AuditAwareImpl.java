package com.microservice.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl") // -> We can provide any name instead of auditAwareImpl
public class AuditAwareImpl implements AuditorAware<String> { // String because createdBy and updatedBy are of type String
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS"); // Hardcoding to 'ACCOUNTS_MS'. Here, MS -> MicroService
        // Later we will try to learn how to populate the logged in user's or client application value.
    }
}
