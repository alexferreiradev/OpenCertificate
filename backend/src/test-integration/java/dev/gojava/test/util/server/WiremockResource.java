package dev.gojava.test.util.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import dev.gojava.test.template.IssueTemplate;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer server = new WireMockServer(Options.DYNAMIC_PORT);

    @Override
    public Map<String, String> start() {
        server.start();

        try {
            createServerStubs();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return createConfigs();
    }

    private void createServerStubs() throws IOException, URISyntaxException {
        server.stubFor(WireMock.get(urlEqualTo("/v1/issues"))
                .willReturn(
                        aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(IssueTemplate.issueValid())
                ));
    }

    private Map<String, String> createConfigs() {
        int portNumber = server.port();
        String url = String.format("http://localhost:%s", portNumber);
        return Map.of("github/mp-rest/url", url);
    }

    @Override
    public void stop() {
        server.stop();
    }
}
