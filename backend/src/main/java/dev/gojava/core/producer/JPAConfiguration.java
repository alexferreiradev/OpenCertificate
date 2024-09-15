package dev.gojava.core.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configure the JPA Repositories to Spring version.
 */
@EnableJpaRepositories(basePackages = "dev.gojava")
@Configuration
public interface JPAConfiguration {}
