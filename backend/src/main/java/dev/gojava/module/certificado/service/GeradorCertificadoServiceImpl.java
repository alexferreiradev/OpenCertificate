package dev.gojava.module.certificado.service;

import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.service.exporter.CertificateExporter;
import dev.gojava.module.certificado.service.generator.CertificateGenerator;
import dev.gojava.module.certificado.service.reader.ParticipantsReader;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GeradorCertificadoServiceImpl implements GeradorCertificadoService {

	@Inject
	ParticipantsReader participantsReader;

	@Inject
	@Default
	CertificateGenerator certificateGenerator;
	@Inject
	CertificateExporter certificateExporter;
	@Inject
	Logger logger;

	@Override
	public void gerarCertificado() {
		logger.info("Carregando lista de participantes de CSV");
		List<Participant> participantList = createParticipantList();
		logger.info("{} participantes encontrados", participantList.size());

		logger.info("Criando lista de certificados");
		List<Certificate> certificateList = createCertificateList(participantList, new String[]{"teste.png"});
		logger.info("{} participantes encontrados", certificateList.size());

		logger.info("Exportando certificados como PDF");
		exportCertificates(certificateList);
		logger.info("Todos certificados exportados com sucesso");
	}

	private List<Participant> createParticipantList() {
		ReaderCommand readerCommand = new ReaderCommand();
		return participantsReader.readParticipant(readerCommand);
	}

	private List<Certificate> createCertificateList(List<Participant> participantList, String[] args) {
		CertificatorGeneratorCommand generatorCommand = new CertificatorGeneratorCommand();
		generatorCommand.setParticipantList(participantList);
		String param = args.length >= 2 ? args[2] : "";
		if (param != null && !param.isEmpty()) {
			generatorCommand.setBackgroundFileName(param);
		}

		return certificateGenerator.generateCertificates(generatorCommand);
	}

	private void exportCertificates(List<Certificate> certificateList) {
		certificateExporter.export(certificateList);
	}
}
