package dev.gojava.test.util.container;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.junit.jupiter.Container;

import java.util.Map;


public class SharedContainerResource implements QuarkusTestResourceLifecycleManager {

    @Container
    public static final PostgresContainer POSTGRES_CONTAINER = new PostgresContainer();

    @Override
    public Map<String, String> start() {
        Map<String, String> quarkusConfiguration = POSTGRES_CONTAINER.createQuarkusConfiguration();
        System.out.println("Config para postgres configurada: " + quarkusConfiguration);
        return quarkusConfiguration;
    }

    @Override
    public void stop() {
    }
}
