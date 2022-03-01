package dev.gojava.module.certificado.service.exporter;

import dev.gojava.core.helper.StreamHelper;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Event;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.service.exporter.metadata.MetadataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


class PdfExporterTest {

    @Mock
    Logger logger;
    @Mock
    MetadataGenerator metadataGenerator;

    @InjectMocks
    PdfExporter exporter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    public class SuccessCases {

        @Test
        void shouldExportWhenParametersValid() throws URISyntaxException, IOException {
            List<Certificate> certificateList = new ArrayList<>();
            Certificate certificate = new Certificate();
            certificate.setId(1L);
            certificate.setFileName("fileName");
            certificate.setFileExtension("pdf");
            byte[] fakePdfContent = "teste pdf content".getBytes(StandardCharsets.UTF_8);
            certificate.setFileContent(fakePdfContent);
            certificate.setUuid(UUID.randomUUID().toString());
            Participant participant = new Participant();
            participant.setCpf("09876512345");
            Event event = new Event();
            event.setName("Teste event");
            event.setTalkerTopics("topics");
            event.setDateStarted(new Date(12431123L));
            event.setDateEnded(new Date(124315677L));
            event.setExecutor("Executor");
            participant.setEvent(event);
            participant.setLastName("last name");
            participant.setHour("4 hours");
            certificate.setParticipant(participant);
            certificateList.add(certificate);

            Mockito.when(metadataGenerator.createMetadataBytes(ArgumentMatchers.any())).thenReturn(new byte[]{0});
            Mockito.when(metadataGenerator.getFileName(ArgumentMatchers.any())).thenReturn("fileNameMetadata");

            List<File> fileList = exporter.export(certificateList);

            assert fileList.size() == 1;
            File firstFile = fileList.get(0);

            FileInputStream stream = new FileInputStream(firstFile);
            Assertions.assertEquals("teste pdf content", StreamHelper.parseStream(stream));
            stream.close();

            Mockito.verify(logger).info("Iniciando exportação de pdf de certificados");
            Mockito.verify(logger).debug("Exportando certificado: fileName");
            Mockito.verify(logger).debug("Certificado exportado");
            Mockito.verify(logger).debug("Certificados exportados");
            Mockito.verify(logger).debug("Meta dados de exportação serão gerados para arquivo: fileNameMetadata");
            Mockito.verify(metadataGenerator).getFileName(certificate);
            Mockito.verify(metadataGenerator).createMetadataBytes(certificateList);

            Mockito.verifyNoMoreInteractions(logger, metadataGenerator);
        }
    }
}
