package dev.gojava.module.certificado.api;

import dev.gojava.core.producer.LoggerProducer;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.CertificadoService;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.nio.file.Files;

@WebMvcTest({CertificadoRest.class})
@Import({LoggerProducer.class})
public class CertificadoRestLightIT {

    @MockBean
    CertificadoService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturn200_whenInputIsValid() throws Exception {
        CertificadoGeradoDTO dto = new CertificadoGeradoDTO();
        dto.arquivoZIP = Files.createTempFile("test",".zip").toFile();
        Mockito.when(service.criarListaCertificado(Mockito.any())).thenReturn(dto);

        GenerateCertForm form = new GenerateCertForm();
        form.entityName = GeneratorType.GOJAVA.name();
        form.csvFile = new File(getClass().getResource("/certificado-rest/event-sample.csv").toURI());

        // @formatter:off
        byte[] content = Files.readAllBytes(form.csvFile.toPath());
        String partName = "go-java";
        mockMvc.perform(MockMvcRequestBuilders.multipart("/certificados")
                .file("file", content)
                .param("entityName", partName)
        ).andExpectAll(
                MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("entity").value(partName),
                MockMvcResultMatchers.jsonPath("zipFile").value(dto.arquivoZIP.getAbsolutePath())
        );
    }

    @Test
    public void shouldReturn200_whenZipIsValid() throws Exception {
        String partName = "test";
        Mockito.when(service.criarListaCertificado(Mockito.any())).thenReturn(new CertificadoGeradoDTO());

        File zipFile = new File(getClass().getResource("/certificado-rest/valid.zip").toURI());
        // @formatter:off
        byte[] content = Files.readAllBytes(zipFile.toPath());
        Mockito.when(service.getZip(partName)).thenReturn(content);
        mockMvc.perform(MockMvcRequestBuilders.get("/certificados/zipfiles")
                .queryParam("filePath", partName)
        ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_OCTET_STREAM),
            MockMvcResultMatchers.content().bytes(content)
        );
    }
}
