package dev.gojava.module.certificado.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainerIT extends IntegrationTest {

    @Test
    void should_start_container() {
        POSTGRES_CONTAINER.start();
        Assertions.assertTrue(POSTGRES_CONTAINER.isRunning());
    }
}
