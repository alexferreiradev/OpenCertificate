package dev.gojava.test.util.container;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

@Testcontainers
public class LocalContainerResource implements QuarkusTestResourceLifecycleManager {

    @Container
    public PostgresContainer postgresContainer = new PostgresContainer();

    @Override
    public Map<String, String> start() {
        Map<String, String> quarkusConfiguration = postgresContainer.createQuarkusConfiguration();
        System.out.println("Config para postgres configurada: " + quarkusConfiguration);
        return quarkusConfiguration;
    }

    @Override
    public void stop() {
    }
}
