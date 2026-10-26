package com.example.moneylens.infrastructure;

import com.example.moneylens.application.model.AppUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

    /** Lấy userId của người đang đăng nhập (từ JWT) để ghi vào created_by / updated_by. */
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }
            Object principal = auth.getPrincipal();
            if (principal instanceof AppUserDetails userDetails) {
                return Optional.of(userDetails.getUserId());
            }
            return Optional.of(principal.toString());
        };
    }
}
