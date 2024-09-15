package dev.gojava.module.certificado.repository;

import dev.gojava.module.certificado.model.Certificate;
import org.springframework.data.repository.CrudRepository;

public interface SpringCertificadoRepository extends CrudRepository<Certificate, Long> {

}
