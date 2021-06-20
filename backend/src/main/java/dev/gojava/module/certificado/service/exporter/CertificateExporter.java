package dev.gojava.module.certificado.service.exporter;

import dev.gojava.module.certificado.model.Certificate;

import java.util.List;

public interface CertificateExporter {

	void export(List<Certificate> certificates);

}
