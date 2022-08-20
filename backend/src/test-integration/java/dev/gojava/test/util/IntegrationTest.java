package dev.gojava.test.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.gojava.test.util.container.SharedContainerResource;
import dev.gojava.test.util.server.InjectableTest;
import dev.gojava.test.util.server.WiremockResource;
import io.quarkus.test.common.QuarkusTestResource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@QuarkusTestResource(value = SharedContainerResource.class)
@QuarkusTestResource(value = WiremockResource.class, restrictToAnnotatedClass = true)
public abstract class IntegrationTest implements InjectableTest {

    protected WireMockServer fakeServer;

    @Override
    public void injectWireMockServer(WireMockServer server) {
        this.fakeServer = server;
    }
}
