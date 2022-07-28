package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.api.test.commons.container.CustomPostgresContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class IntegrationTest {

    @Container
    protected final static CustomPostgresContainer POSTGRES_CONTAINER = new CustomPostgresContainer();

}
