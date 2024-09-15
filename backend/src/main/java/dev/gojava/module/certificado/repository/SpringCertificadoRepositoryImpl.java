package dev.gojava.module.certificado.repository;

import dev.gojava.module.certificado.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpringCertificadoRepositoryImpl implements CertificadoRepository {

    @Autowired
    SpringCertificadoRepository repository;

    @Override
    public void persist(Certificate certificate) {
        repository.save(certificate);
    }

    @Override
    public void persist(Iterable<Certificate> certificates) {
        repository.saveAll(certificates);
    }
}
