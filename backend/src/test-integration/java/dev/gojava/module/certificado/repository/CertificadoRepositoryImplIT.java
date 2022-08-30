package dev.gojava.module.certificado.repository;

import dev.gojava.module.certificado.api.IntegrationTest;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Event;
import dev.gojava.module.certificado.model.Participant;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.james.mime4j.dom.datetime.DateTime;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
class CertificadoRepositoryImplIT extends IntegrationTest {

    @Inject
    CertificadoRepository repository;

    @Test
    void shouldSaveCertificateWhenCallSave() {
        Certificate entity = new Certificate();
        Participant participant = new Participant();
        Event event = new Event();
        event.setName("teste");
        event.setExecutor("teste");
        event.setTalkerTopics("teste");
        event.setDateStarted(new Date());
        event.setDateEnded(new Date());

        participant.setEvent(event);
        participant.setName("teste");
        participant.setCpf("teste");
        participant.setLastName("teste");
        participant.setRg("teste");
        participant.setHour("teste");

        entity.setParticipant(participant);
        entity.setFileName("teste");
        entity.setFileExtension("pdf");
        entity.setUuid("teste");
        repository.persist(entity);

        Certificate firstResult = repository.findById(entity.getId());
        assertEquals(entity.getFileName(), firstResult.getFileName());
    }
}
