package dev.gojava.module.certificado.service.exporter;

import dev.gojava.module.certificado.model.Certificate;

import java.io.File;
import java.util.List;

/**
 * Interface utilizada para exportar certificados.
 *
 * @see dev.gojava.module.certificado.service.exporter.PdfExporter
 */
public interface CertificateExporter {

    /**
     * Exporta a lista de certificados para determinado tipo de acordo com implementação.
     *
     * @param certificates lista de certificados a serem exportados.
     * @return lista de arquivos pdf gerados.
     */
    List<File> export(List<Certificate> certificates);

}
