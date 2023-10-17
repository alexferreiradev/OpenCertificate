package dev.gojava.core.util;

import dev.gojava.common.fixture.CertificateFixture;
import dev.gojava.module.certificado.model.Certificate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateUtilTest {


    @Test
    void deveRetornarNomeDeArquivo_quandoCertificadoSerValido() {
        Certificate certificate = CertificateFixture.create();
        certificate.setFileName("test");
        certificate.setFileExtension("test");
        String fileName = CertificateUtil.createFileName(certificate);

        assertEquals(certificate.getFileName()+"."+ certificate.getFileExtension(), fileName);
    }

    @Test
    void deveLancarErro_quandoCertificadoSerNulo_() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> CertificateUtil.createFileName(null));

        assertEquals("Certificado não é válido, não possui nome de arquivo ou extensão", exception.getMessage());
    }
}
