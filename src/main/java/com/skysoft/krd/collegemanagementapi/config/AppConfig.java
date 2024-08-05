package com.skysoft.krd.collegemanagementapi.config;

import com.skysoft.krd.collegemanagementapi.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AppConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }
}
