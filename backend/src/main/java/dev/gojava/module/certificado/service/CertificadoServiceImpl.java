package dev.gojava.module.certificado.service;

import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.service.exporter.CertificateExporter;
import dev.gojava.module.certificado.service.generator.CertificateGenerator;
import dev.gojava.module.certificado.service.reader.ParticipantsReader;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CertificadoServiceImpl implements CertificadoService {

    public static final String ARQUIVO_BACKGROUND_GOJAVA = "ArquivoGoJava.png";
    @Inject
    Logger logger;

    @Inject
    ParticipantsReader reader;
    @Inject
    CertificateGenerator generator;
    @Inject
    CertificateExporter exporter;


    @Override
    public CertificadoGeradoDTO criarListaCertificado(CertificadoCommand command) {
        logger.info("Iniciando geração de certificado para: {}", command);

        ReaderCommand readerCommand = command.getReaderCommand();
        List<Participant> participants = reader.readParticipant(readerCommand);
        CertificatorGeneratorCommand generatorCommand = CertificatorGeneratorCommand.from(participants, ARQUIVO_BACKGROUND_GOJAVA);
        List<Certificate> certificates = generator.generateCertificates(generatorCommand);
        salvarCertificadosNoBancoOrThrow(certificates);
        exporter.export(certificates);

        CertificadoGeradoDTO dto = new CertificadoGeradoDTO();
        return dto;
    }

    private void salvarCertificadosNoBancoOrThrow(List<Certificate> certificates) {
        logger.info("Salvando {} certificados no banco", certificates.size());
        // todo adicionar panache e salvar dados
        logger.info("Certificados salvos no banco");
    }
}
