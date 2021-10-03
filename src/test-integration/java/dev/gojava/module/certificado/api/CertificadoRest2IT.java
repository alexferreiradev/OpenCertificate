package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;

@QuarkusTest
class CertificadoRest2IT {

    @Test
    void salvarListaCertificado() throws URISyntaxException {
        GenerateCertForm generateForm = new GenerateCertForm();
        generateForm.csvFile = new File(getClass().getResource("/api/certificado/csv-valid.csv").toURI());
        generateForm.entityName = GeneratorType.GOJAVA.name();

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", generateForm.entityName)
                .multiPart("csv", generateForm.csvFile)
                .post("/certificados")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }
}