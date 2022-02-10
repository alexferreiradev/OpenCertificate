package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.CertificadoService;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
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
public class CertificadoRestIT {

    @InjectMock
    CertificadoService service;

    CertificadoGeradoDTO dto;

    @Inject
    CertificadoRest rest;

    @BeforeEach
    void setUp() {
        dto = new CertificadoGeradoDTO();
        dto.arquivoZIP = new File("./");
    }

    @Test
    public void returnOk() throws URISyntaxException {
        Mockito.when(service.criarListaCertificado(Mockito.any())).thenReturn(dto);

        GenerateCertForm form = new GenerateCertForm();
        form.entityName = GeneratorType.GOJAVA.name();
        form.csvFile = new File(getClass().getResource("/certificado-rest/csvOk.csv").toURI());
        Response response = rest.salvarListaCertificado(form);

        MatcherAssert.assertThat(response.getStatus(), equalTo(200));
    }
}
