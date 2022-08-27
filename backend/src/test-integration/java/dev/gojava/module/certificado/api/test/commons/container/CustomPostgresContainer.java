package dev.gojava.module.certificado.api.test.commons.container;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomPostgresContainer extends PostgreSQLContainer<CustomPostgresContainer> implements InjectedContainer {

    public CustomPostgresContainer() {
        super(DockerImageName.parse("postgres:13-alpine").asCompatibleSubstituteFor("postgres"));
        withUsername("postgres");
        withPassword("postgres");
        withDatabaseName("open-certificate");
        withExposedPorts(5432);
        withExposedPorts(5432);

        start();

        Flyway.configure().dataSource(getJdbcUrl(), getUsername(), getPassword()).load().migrate();
    }

    @Override
    public Map<String, String> configureProperties() {
        return Map.of("%test.quarkus.datasource.jdbc.url", getJdbcUrl());
    }
}
