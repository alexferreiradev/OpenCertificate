package dev.gojava.module.certificado.repository;

import dev.gojava.module.certificado.api.IntegrationTest;
import dev.gojava.module.certificado.model.Certificate;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
class CertificadoRepositoryImplIT extends IntegrationTest {

    @Inject
    CertificadoRepository repository;

    @Test
    void shouldSaveCertificateWhenCallSave() {
        Certificate entity = new Certificate();
        repository.persist(entity);

        Certificate firstResult = repository.findById(entity.getId());
        assertEquals(entity.getFileName(), firstResult.getFileName());
    }
}
