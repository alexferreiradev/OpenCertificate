package dev.gojava.module.certificado.service;

import dev.gojava.core.exception.RestApplicationException;
import dev.gojava.core.helper.FileHelper;
import dev.gojava.core.helper.zip.ZipFileNotCreatedException;
import dev.gojava.core.helper.zip.ZipHelper;
import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.repository.CertificadoRepository;
import dev.gojava.module.certificado.service.exporter.CertificateExporter;
import dev.gojava.module.certificado.service.generator.CertificateGenerator;
import dev.gojava.module.certificado.service.reader.ParticipantsReader;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

@ApplicationScoped
public class CertificadoServiceImpl implements CertificadoService {

    public static final String ARQUIVO_BACKGROUND_GOJAVA = "ArquivoGoJava.png";
    private static final int PDF_ZIP_BUFFER_SIZE = 1024;
    private static final String ZIP_FILE_PREFIX_NAME = "arquivoCertificadosExportados_";
    private static final String ZIP_FILE_EXTENSION = ".zip";

    @Inject
    Logger logger;

    @Inject
    ParticipantsReader reader;
    @Inject
    @Default
    CertificateGenerator generator;
    @Inject
    CertificateExporter exporter;
    @Inject
    CertificadoRepository repository;


    @Transactional
    @Override
    public CertificadoGeradoDTO criarListaCertificado(CertificadoCommand command) {
        logger.info("Iniciando geração de certificado para: {}", command);

        ReaderCommand readerCommand = reader.createReaderCommandOrThrow(command.form.csvFile);
        List<Participant> participants = reader.readParticipant(readerCommand);
        CertificatorGeneratorCommand generatorCommand = CertificatorGeneratorCommand.from(participants, ARQUIVO_BACKGROUND_GOJAVA);
        List<Certificate> certificates = generator.generateCertificates(generatorCommand);
        salvarCertificadosNoBancoOrThrow(certificates);
        List<File> certPdfList = exporter.export(certificates);

        CertificadoGeradoDTO dto = new CertificadoGeradoDTO();
        dto.arquivoZIP = createZipFileFromPdfList(certPdfList);
        dto.tamanhoZIP = FileHelper.tamanhoArquivo(dto.arquivoZIP);
        return dto;
    }

    private File createZipFileFromPdfList(List<File> certPdfList) {
        try {
            File tempFile = File.createTempFile(ZIP_FILE_PREFIX_NAME, ZIP_FILE_EXTENSION);
            ZipOutputStream zipOut = ZipHelper.createZipOutputstreamTo(tempFile.getAbsolutePath());
            ZipHelper.writeZipFrom(zipOut, certPdfList, PDF_ZIP_BUFFER_SIZE);

            return tempFile;
        } catch (ZipFileNotCreatedException | IOException e) {
            String msg = "Erro ao tentar criar arquivo zip para certificados";
            logger.error(msg, e);
            throw new RestApplicationException(msg, e, 500);
        }
    }

    private void salvarCertificadosNoBancoOrThrow(List<Certificate> certificates) {
        logger.info("Salvando {} certificados no banco", certificates.size());
        repository.persist(certificates);
        logger.info("Certificados salvos no banco");
    }
}
