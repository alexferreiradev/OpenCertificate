package dev.gojava.test.util.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer server = new WireMockServer(Options.DYNAMIC_PORT);

    @Override
    public Map<String, String> start() {
        server.start();

        return createConfigs();
    }

    private Map<String, String> createConfigs() {
        return Map.of("github/mp-rest/url", server.baseUrl());
    }

    @Override
    public void stop() {
        server.stop();
    }

    @Override
    public void inject(Object testInstance) {
        if (testInstance instanceof InjectableTest) {
            InjectableTest injectable = (InjectableTest) testInstance;
            injectable.injectWireMockServer(server);
        } else {
            throw new RuntimeException("Error when try to inject wiremock server in the test, the class is not injectable");
        }
    }
}
