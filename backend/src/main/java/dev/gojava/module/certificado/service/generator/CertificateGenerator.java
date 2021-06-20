package dev.gojava.module.certificado.service.generator;

import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.model.Certificate;

import java.util.List;

public interface CertificateGenerator {

	List<Certificate> generateCertificates(CertificatorGeneratorCommand command);

}
