package dev.gojava.module.certificado.api;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class IntegrationTest {

    @Container
    protected final static GenericContainer POSTGRES_CONTAINER = new PostgreSQLContainer();


}
