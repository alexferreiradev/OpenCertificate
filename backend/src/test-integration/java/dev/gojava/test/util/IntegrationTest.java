package dev.gojava.test.util;

import dev.gojava.test.util.container.SharedContainerResource;
import dev.gojava.test.util.server.WiremockResource;
import io.quarkus.test.common.QuarkusTestResource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@QuarkusTestResource(value = SharedContainerResource.class)
@QuarkusTestResource(value = WiremockResource.class)
public abstract class IntegrationTest {

}
