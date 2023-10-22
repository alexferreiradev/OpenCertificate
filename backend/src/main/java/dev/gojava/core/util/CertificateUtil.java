package dev.gojava.core.util;

import dev.gojava.module.certificado.model.Certificate;

public final class CertificateUtil {

    /**
     * Cria um nome para arquivo de certificado com o padrão: nome.extensao.
     *
     * @param certificate certificado
     * @return nome criado.
     */
    public static String createFileName(Certificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("Certificado não é válido, não possui nome de arquivo ou extensão");
        }

        return String.format("%s.%s", certificate.getFileName(), certificate.getFileExtension());
    }
}
