package com.theniche.colivin.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

@Configuration
public class AuditorConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        // Later integrate with SecurityContextHolder
        return () -> Optional.of("system");
    }
}
