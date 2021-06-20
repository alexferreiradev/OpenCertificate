package dev.gojava.module.certificado.service.generator;

import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.model.Certificate;

import java.util.List;

/**
 * Interface para definir geradores de certificados.
 *
 * @see GoJavaGenerator
 */
public interface CertificateGenerator {

    List<Certificate> generateCertificates(CertificatorGeneratorCommand command);

}
