package dev.gojava.module.certificado.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import dev.gojava.module.github.IssueResponse;
import dev.gojava.module.github.api.GithubApiClient;
import dev.gojava.test.template.IssueTemplate;
import dev.gojava.test.util.IntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

@QuarkusTest
public class WiremockTestExample extends IntegrationTest {

    @RestClient
    GithubApiClient client;

    @Test
    void shouldReturnWiremockMockResponseWhenCallApi() throws IOException, URISyntaxException {
        fakeServer.stubFor(
                WireMock.get(WireMock.urlEqualTo("/v1/issue"))
                        .willReturn(
                                WireMock.aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(IssueTemplate.issueValid())
                        )
        );

        IssueResponse issue = client.createIssue();

        MatcherAssert.assertThat(issue, CoreMatchers.notNullValue());
        MatcherAssert.assertThat(issue.id, CoreMatchers.is("1"));
        MatcherAssert.assertThat(issue.assign, CoreMatchers.is("Alex R Ferreira"));
        MatcherAssert.assertThat(issue.title, CoreMatchers.is("Postar Apresentação no GitHub"));

        fakeServer.verify(WireMock.getRequestedFor(
                WireMock.urlEqualTo("/v1/issues")
        ));
    }
}
