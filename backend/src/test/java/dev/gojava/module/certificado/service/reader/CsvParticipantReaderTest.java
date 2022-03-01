package dev.gojava.module.certificado.service.reader;

import dev.gojava.core.exception.RestApplicationException;
import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.model.Participant;
import org.hamcrest.MatcherAssert;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CsvParticipantReaderTest {

    @InjectMocks
    CsvParticipantReader service;
    @Mock
    Logger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenCsvArquivo_whenCreateReader_thenCriaCommandComArquivoTemp() throws IOException {
        File fakeCsvFile = Files.createTempFile("temp_OpenCertificateTest_createCommand", ".csv").toFile();
        ReaderCommand readerCommand = service.createReaderCommandOrThrow(fakeCsvFile);
        fakeCsvFile.deleteOnExit();

        String regexParaValidarCaminho = MessageFormat.format(".*{0}tempEvento_\\d+.csv", FileSystems.getDefault().getSeparator());
        MatcherAssert.assertThat(readerCommand.absoluteFilePath, matchesRegex(regexParaValidarCaminho));
    }

    @Test
    void givenCsvNotExist_whenCreateReader_thenThrow() {
        File fileNotExist = new File("/notExist.csv");

        RestApplicationException ex = assertThrows(RestApplicationException.class, () -> service.createReaderCommandOrThrow(fileNotExist));

        MatcherAssert.assertThat(ex.getMessage(), is("Não foi possível ler arquivo csv"));
        Mockito.verify(logger).error("Erro ao tentar criar reader", ex.getCause());
    }

    @Test
    void givenCsvCom10Participantes_whenReadParticipant_thenCriaListaParticipantes() throws URISyntaxException {
        URI csvUri = getClass().getResource("/csv-participant/csv10Participantes.csv").toURI();
        File csvWith10Participants = new File(csvUri);
        ReaderCommand command = service.createReaderCommandOrThrow(csvWith10Participants);
        List<Participant> participants = service.readParticipant(command);

        MatcherAssert.assertThat(participants.size(), is(10));
        MatcherAssert.assertThat(participants.get(0).getCpf(), is("12312312390"));
        MatcherAssert.assertThat(participants.get(0).getEvent().getName(), is("Java Jug Tour 2021"));
        MatcherAssert.assertThat(participants.get(0).toString(),
                is("Participant{id=null, name='Airon', lastName='Gonçalves', rg='null', cpf='12312312390', hour='4', event=Event{id=null, name='Java Jug Tour 2021', "
                        + "executor='GoJava', talkerTopics='desenvolvimento ágil, carreira de TI e micro-profile', dateStarted=Fri Jan 01 00:00:00 BRT 2021, dateEnded=Fri Jan 01"
                        + " 00:00:00 BRT 2021}}"));
    }

    @Test
    void givenParcipanteComRG_whenReadParticipant_thenCriaListaNormalComRgNaIdentificacao() throws URISyntaxException {
        URI csvUri = getClass().getResource("/csv-participant/csv1ParticipanteComRG.csv").toURI();
        File csvWithParticipant = new File(csvUri);
        ReaderCommand command = service.createReaderCommandOrThrow(csvWithParticipant);
        List<Participant> participants = service.readParticipant(command);

        MatcherAssert.assertThat(participants.size(), is(1));
        MatcherAssert.assertThat(participants.get(0).getRg(), is("1231230"));
        MatcherAssert.assertThat(participants.get(0).getCpf(), nullValue());
    }

    @Test
    void givenCsvInvalido_whenReadParticipant_thenThrowException() throws URISyntaxException {
        URI csvUri = getClass().getResource("/csv-participant/csvInvalido.csv").toURI();
        File csvWith10Participants = new File(csvUri);
        ReaderCommand command = service.createReaderCommandOrThrow(csvWith10Participants);

        RestApplicationException restApplicationException = assertThrows(RestApplicationException.class, () -> service.readParticipant(command));

        MatcherAssert.assertThat(restApplicationException.getMessage(), is("CSV possívelmente antigo ou incompatível"));
        MatcherAssert.assertThat(restApplicationException.getCause(), isA(IndexOutOfBoundsException.class));
        MatcherAssert.assertThat(restApplicationException.getResponse().getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
        Mockito.verify(logger).error(restApplicationException.getMessage(), restApplicationException.getCause());
    }
}