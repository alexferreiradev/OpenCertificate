package dev.gojava.module.certificado.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

@QuarkusTest
class CertificadoRestTest {

    @Test
    void salvarListaCertificado() {
        // @formatter:off
        RestAssured.given()
                .when()
                .header("Accept", MediaType.APPLICATION_JSON)
                .post("/certificados")
                .then()
                .statusCode(422)
                .body(CoreMatchers.nullValue());
    }
}