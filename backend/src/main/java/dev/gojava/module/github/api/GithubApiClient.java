package dev.gojava.module.github.api;

import dev.gojava.module.github.IssueResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Cliente para api externa do github.
 */
@Path("/v1/issues")
@RegisterRestClient(configKey = "github")
@Produces(MediaType.APPLICATION_JSON)
public interface GithubApiClient {

    @GET
    IssueResponse createIssue();
}
