package dev.gojava.test.util.container;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


public class SharedContainerResource implements QuarkusTestResourceLifecycleManager {

    @Container
    public static final PostgresContainer POSTGRES_CONTAINER = new PostgresContainer();
    @Container
    public static final AzureBlobContainer AZURE_CONTAINER = new AzureBlobContainer();

    @Override
    public Map<String, String> start() {
        Map<String, String> quarkusConfiguration = POSTGRES_CONTAINER.createQuarkusConfiguration();
        System.out.println("Configuration replaced to postgres: " + quarkusConfiguration);
        Map<String, String> azureQuarkusConfiguration = AZURE_CONTAINER.createQuarkusConfiguration();
        System.out.println("Configuration replaced to azure: " + azureQuarkusConfiguration);
        Map<String, String> mergeMap = new HashMap<>();
        mergeMap.putAll(quarkusConfiguration);
        mergeMap.putAll(azureQuarkusConfiguration);
        return ImmutableMap.copyOf(mergeMap);
    }

    @Override
    public void stop() {
    }
}
