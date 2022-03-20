package dev.gojava.module.certificado.service.exporter;

import dev.gojava.core.exception.RestApplicationException;
import dev.gojava.core.helper.FileHelper;
import dev.gojava.core.helper.StreamHelper;
import dev.gojava.core.util.CertificateUtil;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.service.exporter.metadata.MetadataGenerator;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PdfExporter implements CertificateExporter {

    private static final String CERT_DIR_NAME = "certificados";

    @Inject
    Logger logger;
    @Inject
    MetadataGenerator metadataGenerator;

    @Override
    public List<File> export(List<Certificate> certificates) {
        logger.info("Iniciando exportação de pdf de certificados");

        List<File> certPdfFileList = new ArrayList<>();
        for (Certificate certificate : certificates) {
            logger.debug("Exportando certificado: " + certificate.getFileName());
            File pdfFile = exportPDF(certificate);
            certPdfFileList.add(pdfFile);
            logger.debug("Certificado exportado");
        }

        exportMetadata(certificates);
        logger.debug("Certificados exportados");

        return certPdfFileList;
    }

    private void exportMetadata(List<Certificate> certificates) {
        Certificate certificateExample = certificates.get(0);
        String jsonFileName = metadataGenerator.getFileName(certificateExample);
        logger.debug("Meta dados de exportação serão gerados para arquivo: " + jsonFileName);

        File file = new File("./" + CERT_DIR_NAME + "/" + jsonFileName);
        FileOutputStream fileOutputStream = null;
        try {
            createExportDirectory();
            FileHelper.createFileOrThrow(file.getAbsolutePath(), true);

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(metadataGenerator.createMetadataBytes(certificates));
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RestApplicationException("Erro ao tentar exportar certificados", e, 500);
        } finally {
            StreamHelper.closeSafeOutput(fileOutputStream);
        }
    }

    private void createExportDirectory() {
        File certDirExport = new File("./" + CERT_DIR_NAME);
        FileHelper.createDirOrThrow(certDirExport);
    }

    private File exportPDF(Certificate certificate) {
        try {
            File file = Files.createTempFile("certificadoDraft_", CertificateUtil.createFileName(certificate)).toFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
                fileOutputStream.write(certificate.getFileContent());
                fileOutputStream.flush();
                file.deleteOnExit();

                return file;
            }
        } catch (Exception e) {
            throw new RestApplicationException("Erro de IO ao tentar criar arquivo PDF", e);
        }
    }
}
