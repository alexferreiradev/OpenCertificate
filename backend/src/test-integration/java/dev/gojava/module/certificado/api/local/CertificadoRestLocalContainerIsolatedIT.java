package dev.gojava.module.certificado.api.local;

import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import dev.gojava.test.util.LocalContainerIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;


@QuarkusTest
@Testcontainers
public class CertificadoRestLocalContainerIsolatedIT extends LocalContainerIntegrationTest {

//    @Container
//    public PostgresContainer postgresContainer = new PostgresContainer();

    @Test
    void salvarListaCertificado() throws URISyntaxException {
//        postgresContainer.createQuarkusConfiguration();
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
    void salvarListaCertificado1() throws URISyntaxException {
//        postgresContainer.createQuarkusConfiguration();
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
    void salvarListaCertificado2() throws URISyntaxException {
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
    void salvarListaCertificado3() throws URISyntaxException {
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
