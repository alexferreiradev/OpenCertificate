package dev.gojava.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import dev.gojava.module.certificado.model.Certificate;
/**
 * Unit tests for {@link CertificateUtil}.
 *
 * @author João Carvalho (joaomarcosribeiroc@gmail.com)
 */
public final class CertificateUtilTest {

    @Test
    public void givenCertificateNull_WhenCreateFileName_ThenThrowsIllegalArgumentException(){
            Assertions.assertThrows(IllegalArgumentException.class, new Executable(){
            @Override
            public void execute() throws Throwable{
                Certificate certificate = null;
                CertificateUtil.createFileName(certificate);
            }
        });
    }
    
    @Test
    public void givenCertificateNull_WhenCreateFileName_ThenMessageReturnedProperly(){
        Certificate certificate = null;
        String message = null;
        try{
            CertificateUtil.createFileName(certificate);
        }catch(IllegalArgumentException iae){
            message = iae.getMessage();
        }
        Assertions.assertEquals("Certificado não é válido, não possui nome de arquivo ou extensão",message);
    }

    @Test
    public void givenCertificateCorrect_WhenCreateFileName_ThenCertifacateNameReturnedProperly(){
        final String fileName = "filename";
        final String fileExtension = "fileExtension";

        Certificate certificate = new Certificate();
        certificate.setFileName(fileName);
        certificate.setFileExtension(fileExtension);

        String resultingFileName = CertificateUtil.createFileName(certificate);

        Assertions.assertEquals(resultingFileName, "filename.fileExtension");
    }
}
