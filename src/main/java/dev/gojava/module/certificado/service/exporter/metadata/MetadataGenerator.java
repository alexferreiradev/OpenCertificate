package dev.gojava.module.certificado.service.exporter.metadata;

import dev.gojava.module.certificado.model.Certificate;

import java.util.List;

/**
 * Interface para definir geradores de metadados. Metadados que serão salvos junto com pasta de certificados
 * que poderão ser utilizados para auditoria.
 *
 * @see JsonMetadataGenerator
 */
public interface MetadataGenerator {

    byte[] createMetadataBytes(List<Certificate> certificateList);

    String getFileName(Certificate certificate);
}
