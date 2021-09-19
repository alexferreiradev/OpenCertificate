package dev.gojava.module.certificado.service;

import dev.gojava.core.exception.RestApplicationException;
import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.repository.CertificadoRepository;
import dev.gojava.module.certificado.service.exporter.CertificateExporter;
import dev.gojava.module.certificado.service.generator.CertificateGenerator;
import dev.gojava.module.certificado.service.reader.ParticipantsReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


class CertificadoServiceImplTest {

    @Mock
    ParticipantsReader reader;
    @Mock
    CertificateGenerator gerador;
    @Mock
    CertificateExporter exporter;
    @Mock
    Logger logger;
    @Mock
    CertificadoRepository repository;

    @InjectMocks
    CertificadoServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarListaCertificado() throws Throwable {
        CertificadoCommand command = new CertificadoCommand();
        command.form = Mockito.mock(GenerateCertForm.class);
        List<Participant> listaFakeParticipante = new ArrayList<>();
        List<Certificate> listaFakeCertificados = new ArrayList<>();
        List<File> listaFakeArquivos = new ArrayList<>();
        listaFakeArquivos.add(crieTempFile("test.pdf"));
        listaFakeArquivos.add(crieTempFile("test2.pdf"));
        command.form.csvFile = crieTempFile("teste.csv");

        Mockito.when(reader.readParticipant(Mockito.any())).thenReturn(listaFakeParticipante);
        Mockito.when(gerador.generateCertificates(Mockito.any())).thenReturn(listaFakeCertificados);
        Mockito.when(exporter.export(listaFakeCertificados)).thenReturn(listaFakeArquivos);

        CertificadoGeradoDTO certificadoGeradoDTO = service.criarListaCertificado(command);

        MatcherAssert.assertThat(certificadoGeradoDTO.arquivoZIP.getName(), Matchers.endsWith(".zip"));
        MatcherAssert.assertThat(certificadoGeradoDTO.tamanhoZIP, CoreMatchers.is(0L));
        Mockito.verify(repository).persist(listaFakeCertificados);
        Mockito.verify(logger).info("Iniciando geração de certificado para: {}", command);
        Mockito.verify(repository).persist(listaFakeCertificados);
    }

    @Test
    void throwsFileNotFound_whenCsvNotFound() throws IOException {
        CertificadoCommand command = new CertificadoCommand();
        command.form = Mockito.mock(GenerateCertForm.class);
        command.form.csvFile = crieTempFile("teste.csv");
        Mockito.when(reader.createReaderCommandOrThrow(command.form.csvFile))
                .thenThrow(new RestApplicationException("Não foi possível ler arquivo csv", null, Response.Status.BAD_REQUEST));

        Assertions.assertThrows(RestApplicationException.class, () -> {
            service.criarListaCertificado(command);

            Mockito.verify(reader, Mockito.only()).createReaderCommandOrThrow(Mockito.any());
        });
    }

    private File crieTempFile(String fileName) throws IOException {
        Path tempFile = Files.createTempFile("teste-service-certificado_", fileName);
        Files.writeString(tempFile, "Teste de pdf com zip", StandardOpenOption.WRITE);

        return tempFile.toFile();
    }
}
