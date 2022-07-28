package dev.gojava.module.certificado.api.test.commons.container;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomPostgresContainer<SELF extends PostgreSQLContainer<SELF>> extends PostgreSQLContainer<SELF> implements InjectedContainer {

    public CustomPostgresContainer() {
        super(DockerImageName.parse("postgres:13-alpine").asCompatibleSubstituteFor("postgres"));

        start();

        Flyway.configure()
                .dataSource(getJdbcUrl(), getUsername(), getPassword())
                .load()
                .migrate();
    }

    @Override
    public Map<String, String> configureProperties() {
        return Map.of("jdbcUrl", getJdbcUrl());
    }
}
