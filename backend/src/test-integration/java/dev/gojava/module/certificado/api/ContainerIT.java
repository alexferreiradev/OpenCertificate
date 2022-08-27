package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.api.test.commons.container.CustomPostgresContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;

public class ContainerIT {

    @Container
    protected final static CustomPostgresContainer POSTGRES_CONTAINER = new CustomPostgresContainer();

    @Test
    void should_start_container() {
        POSTGRES_CONTAINER.start();
        Assertions.assertTrue(POSTGRES_CONTAINER.isRunning());
    }
}
