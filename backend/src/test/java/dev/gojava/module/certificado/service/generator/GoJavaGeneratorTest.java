package dev.gojava.module.certificado.service.generator;

import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Event;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.service.generator.token.TokenGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class GoJavaGeneratorTest {

    @Spy
    Logger logger;
    @Mock
    TokenGenerator tokenGenerator;

    @InjectMocks
    private GoJavaGenerator generator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(tokenGenerator.generateToken(ArgumentMatchers.any(Certificate.class))).thenReturn("tokenSha256");
    }

    @Test
    void generateCertificates() throws IOException {
        CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();
        List<Participant> participantList = new ArrayList<>();
        Participant participant = new Participant();
        participant.setName("Teste 1");
        participant.setLastName("Last name 1");
        participant.setRg("12312312312");
        participant.setHour("hour 2");
        Event event = new Event();
        event.setName("Evento x");
        event.setDateStarted(new Date(123123123L));
        event.setTalkerTopics("talkers");
        participant.setEvent(event);
        participantList.add(participant);
        command.setParticipantList(participantList);

        List<Certificate> certificates = generator.generateCertificates(command);

        Assertions.assertNotNull(certificates);
        Assertions.assertEquals(1, certificates.size());
        Certificate firstCertificate = certificates.get(0);

        Assertions.assertEquals("Teste 1", firstCertificate.getParticipant().getName());
        Assertions.assertEquals("Last name 1", firstCertificate.getParticipant().getLastName());
        byte[] bytesExpected = this.getClass().getResourceAsStream("/generator/valid_certificate.pdf").readAllBytes();
        Assertions.assertEquals(bytesExpected.length, firstCertificate.getFileContent().length);
    }
}
