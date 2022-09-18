package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.api.test.commons.container.CustomPostgresContainer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.junit.jupiter.Container;

import java.util.Map;

public class SharedContainerResource implements QuarkusTestResourceLifecycleManager {
    @Container
    protected final static CustomPostgresContainer POSTGRES_CONTAINER = new CustomPostgresContainer();

    @Override
    public Map<String, String> start() {
        return POSTGRES_CONTAINER.configureProperties();
    }

    @Override
    public void stop() {
        POSTGRES_CONTAINER.stop();
    }
}
