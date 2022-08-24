package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.CertificadoService;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CertificadoRestIT extends IntegrationTest {

    @Test
    public void returnOk() throws URISyntaxException {
        GenerateCertForm form = new GenerateCertForm();
        form.entityName = GeneratorType.GOJAVA.name();
        form.csvFile = new File(getClass().getResource("/certificado-rest/csvOk.csv").toURI());

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", form.entityName)
                .multiPart("csv", form.csvFile)
                .post("/certificados")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }
}
