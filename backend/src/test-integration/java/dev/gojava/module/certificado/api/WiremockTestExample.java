package dev.gojava.module.certificado.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import dev.gojava.module.github.IssueResponse;
import dev.gojava.module.github.api.GithubApiClient;
import dev.gojava.test.template.IssueTemplate;
import dev.gojava.test.util.IntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@QuarkusTest
public class WiremockTestExample extends IntegrationTest {

    @RestClient
    GithubApiClient client;

    @Test
    void shouldReturnWiremockMockResponseWhenCallApi() throws IOException, URISyntaxException {
        IssueResponse issue = client.createIssue();

        MatcherAssert.assertThat(issue, CoreMatchers.notNullValue());
        MatcherAssert.assertThat(issue.id, CoreMatchers.is("1"));
        MatcherAssert.assertThat(issue.assign, CoreMatchers.is("Alex R Ferreira"));
        MatcherAssert.assertThat(issue.title, CoreMatchers.is("Postar Apresentação no GitHub"));
    }
}
