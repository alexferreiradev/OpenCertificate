package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.api.test.commons.container.CustomPostgresContainer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class SharedContainerResource implements QuarkusTestResourceLifecycleManager {

    public final static CustomPostgresContainer POSTGRES_CONTAINER = new CustomPostgresContainer();

    static {
        POSTGRES_CONTAINER.start();
    }

    @Override
    public Map<String, String> start() {
        return POSTGRES_CONTAINER.configureProperties();
    }

    @Override
    public void stop() {
    }
}
