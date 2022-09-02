package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@QuarkusTest
public class CertificadoRestIT extends IntegrationTest {

    @Test
    public void shouldReturn200_whenInputIsValid() throws URISyntaxException {
        GenerateCertForm form = new GenerateCertForm();
        form.entityName = GeneratorType.GOJAVA.name();
        form.csvFile = new File(getClass().getResource("/certificado-rest/event-sample.csv").toURI());

        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", form.entityName)
                .multiPart("csv", form.csvFile)
                .post("/certificados")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body(new TypeSafeMatcher<String>() {
                    @Override protected boolean matchesSafely(String item) {
                        System.out.println(item);
                        Logger.getGlobal().info("item: " + item);
                        return true;
                    }

                    @Override public void describeTo(Description description) {
                        description.appendText("não bate o resultado");
                    }
                })
        ;
    }

    @Test
    public void shouldReturn400_whenInputIsinvalid() {
        // @formatter:off
        RestAssured.given()
                .when()
                .multiPart("entityName", "entity that dont exists")
                .multiPart("csv", "fake csv file")
                .post("/certificados")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }
}
