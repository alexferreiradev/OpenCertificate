package dev.gojava.test.util;

import dev.gojava.test.util.container.SharedContainerResource;
import io.quarkus.test.common.QuarkusTestResource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@QuarkusTestResource(value = SharedContainerResource.class)
public abstract class IntegrationTest {}
