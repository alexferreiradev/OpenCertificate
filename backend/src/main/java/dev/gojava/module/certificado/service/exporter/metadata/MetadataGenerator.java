package dev.gojava.module.certificado.service.exporter.metadata;

import dev.gojava.module.certificado.model.Certificate;

import java.util.List;

public interface MetadataGenerator {

	byte[] createMetadataBytes(List<Certificate> certificateList);

	String getFileName(Certificate certificate);
}
