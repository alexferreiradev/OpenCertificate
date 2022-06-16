package dev.gojava.test.util;

import dev.gojava.test.util.container.LocalContainerResource;
import dev.gojava.test.util.container.SharedContainerResource;
import io.quarkus.test.common.QuarkusTestResource;
import org.testcontainers.junit.jupiter.Testcontainers;

@QuarkusTestResource(value = LocalContainerResource.class, restrictToAnnotatedClass = true)
public abstract class LocalContainerIntegrationTest {}
