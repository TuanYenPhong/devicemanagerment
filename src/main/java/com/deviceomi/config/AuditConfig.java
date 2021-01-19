package com.deviceomi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;


@Configuration
@EnableJpaAuditing
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {
//    @Bean
//    public AuditorAware<String> auditorProvider(){
//        return new AuditorAwareImpl();
//    }
//    public static class AuditorAwareImpl implements AuditorAware<String>{
//
//        @Override
//        public Optional<String> getCurrentAuditor() {
//            return Optional.empty();
//        }
//    }
}
