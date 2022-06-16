package dev.gojava.test.util.container;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class PostgresContainer extends PostgreSQLContainer implements ConfigurableContainer {

    public PostgresContainer() {
        super(DockerImageName.parse("postgres:12.11-alpine3.16").asCompatibleSubstituteFor("postgres"));
        this.withDatabaseName("open-certificate");
        this.withUsername("postgres");
        this.withPassword("postgres");
        this.start();
    }

    @Override
    public Map<String, String> createQuarkusConfiguration() {
        return ImmutableMap.of("%test.quarkus.datasource.jdbc.url", this.getJdbcUrl());
    }
}
