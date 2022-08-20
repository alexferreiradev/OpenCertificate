package dev.gojava.module.certificado.api.local;

import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import dev.gojava.test.util.LocalContainerIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;


@QuarkusTest
public class CertificadoRestLocalContainerIT extends LocalContainerIntegrationTest {

    @Test
    void shouldGenerateCertificateWhenRequestIsValid() throws URISyntaxException {
        GenerateCertForm generateForm = new GenerateCertForm();
        generateForm.csvFile = new File(getClass().getResource("/api/certificate/csv-valid.csv").toURI());
        generateForm.entityName = GeneratorType.GOJAVA.name();

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", generateForm.entityName)
                .multiPart("csv", generateForm.csvFile)
                .post("/certificates")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void shouldGenerateCertificateWhenRequestIsValid2() throws URISyntaxException {
        GenerateCertForm generateForm = new GenerateCertForm();
        generateForm.csvFile = new File(getClass().getResource("/api/certificate/csv-valid.csv").toURI());
        generateForm.entityName = GeneratorType.GOJAVA.name();

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", generateForm.entityName)
                .multiPart("csv", generateForm.csvFile)
                .post("/certificates")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void shouldGenerateCertificateWhenRequestIsValid3() throws URISyntaxException {
        GenerateCertForm generateForm = new GenerateCertForm();
        generateForm.csvFile = new File(getClass().getResource("/api/certificate/csv-valid.csv").toURI());
        generateForm.entityName = GeneratorType.GOJAVA.name();

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", generateForm.entityName)
                .multiPart("csv", generateForm.csvFile)
                .post("/certificates")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void shouldGenerateCertificateWhenRequestIsValid4() throws URISyntaxException {
        GenerateCertForm generateForm = new GenerateCertForm();
        generateForm.csvFile = new File(getClass().getResource("/api/certificate/csv-valid.csv").toURI());
        generateForm.entityName = GeneratorType.GOJAVA.name();

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", generateForm.entityName)
                .multiPart("csv", generateForm.csvFile)
                .post("/certificates")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }
}
