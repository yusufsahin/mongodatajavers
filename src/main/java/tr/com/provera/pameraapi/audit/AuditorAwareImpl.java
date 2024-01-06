package tr.com.provera.pameraapi.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Implement logic to retrieve the current user
        // For example, from the security context or session
        return Optional.of("system");
    }
}