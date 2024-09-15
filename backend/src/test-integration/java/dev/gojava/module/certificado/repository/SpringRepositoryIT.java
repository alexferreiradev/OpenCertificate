package dev.gojava.module.certificado.repository;

import dev.gojava.module.certificado.api.test.commons.BaseRepositoyIT;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Event;
import dev.gojava.module.certificado.model.Participant;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringRepositoryIT extends BaseRepositoyIT {
    @Inject
    SpringCertificadoRepository repository;

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
        repository.save(entity);

        Certificate firstResult = repository.findById(entity.getId()).get();
        assertEquals(entity.getFileName(), firstResult.getFileName());
    }
}
