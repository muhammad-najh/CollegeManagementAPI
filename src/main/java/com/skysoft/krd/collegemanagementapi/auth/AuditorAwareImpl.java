package com.skysoft.krd.collegemanagementapi.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

// who do this changes will be appear here
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get secuirty context
        //get Authintication
        //get the princible
        //get the username
        return Optional.of("Muhammad");
    }
}
