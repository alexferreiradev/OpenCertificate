package dev.gojava.module.certificado.service.generator;

import dev.gojava.core.helper.StreamHelper;
import dev.gojava.core.util.GeneratorUtil;
import dev.gojava.core.util.ParticipantUtil;
import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.exception.LargeNameException;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.service.generator.token.TokenGenerator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NasaHackGenerator implements CertificateGenerator {

	private static final int MAX_NAME_CHARS = 29;

	@Inject
	TokenGenerator tokenGenerator;


	@Override
	public List<Certificate> generateCertificates(CertificatorGeneratorCommand command) {
		List<Certificate> certificateList = new ArrayList<>();

		List<Participant> participantList = command.getParticipantList();

		for (Participant participant : participantList) {
			Certificate certificate = new Certificate();
			certificate.setParticipant(participant);

			String participantNamePath = ParticipantUtil.participantCompleteName(participant).replaceAll(" ", "_");
			participantNamePath = participantNamePath.replaceAll("[^\\w]", "-");
			certificate.setFileName("certificado_"+ participantNamePath);
			certificate.setFileExtension("pdf");
			certificate.setUuid(tokenGenerator.generateToken(certificate));
			try {
				certificate.setFileContent(buildPdfFileContent(certificate, command));
			} catch (LargeNameException e) {
				e.printStackTrace();
				throw new IllegalStateException("Erro ao tentar gerar nome para participante", e);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException("Erro ao tentar gerar pdf", e);
			}

			certificateList.add(certificate);
		}

		return certificateList;
	}

	private byte[] buildPdfFileContent(Certificate certificate, CertificatorGeneratorCommand command) throws LargeNameException {
		FileOutputStream fileOutputStream = null;
		Document document = null;

		try {
			File certificateTemp = File.createTempFile("certificateTemp_", ".pdf");
			fileOutputStream = new FileOutputStream(certificateTemp);

			document = new Document(PageSize.A4.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			document.open();

			PdfContentByte cb = writer.getDirectContent();
			ColumnText ct = new ColumnText(cb);
			/**
			 * llx = lower left x
			 * urx = upper right x
			 * 220, 285, 400, 40
			 */
			ct.setSimpleColumn(220, 285, 400 + 220, 285 + 40);
//			ct = new ColumnText(cb);
//			ct.setSimpleColumn(150f, 808f, 300f, 0f);
//			ct.addElement(pz);
//			ct.go();
//			cb.rectangle(220, 285, 400, 40);
//			cb.stroke();
//			ct.go();

			Font paragraphFont = GeneratorUtil.createFontToCertText();
			paragraphFont.setColor(BaseColor.BLACK);
			paragraphFont.setStyle(Font.BOLD);
			paragraphFont.setFamily(Font.FontFamily.HELVETICA.name());
			paragraphFont.setSize(25);
			Paragraph paragraph = createParticipantName(certificate, paragraphFont);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			ct.addElement(paragraph);
			ct.go();

			GeneratorUtil.addBackgroundImage(writer.getDirectContentUnder(), command);

			document.close();
			certificateTemp.deleteOnExit();

			return FileUtils.readFileToByteArray(certificateTemp);
		} catch (IOException | DocumentException | URISyntaxException e) {
			e.printStackTrace();
		} catch (LargeNameException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (document != null) {
				document.close();
			}
			StreamHelper.closeSafeOutput(fileOutputStream);
		}

		return null;
	}

	private Paragraph createParticipantName(Certificate certificate, Font paragraphFont) throws LargeNameException {
		Paragraph paragraph = new Paragraph();
		paragraph.setLeading(0f, 1.1f);
		paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		paragraph.setFont(paragraphFont);
		Participant participant = certificate.getParticipant();
		String participantCompleteName = ParticipantUtil.participantCompleteName(participant);
		if (participantCompleteName.length() > MAX_NAME_CHARS) {
			participantCompleteName = ParticipantUtil.participantCompleteNameSummarized(participant);
			if (participantCompleteName.length() > MAX_NAME_CHARS) {
				throw new LargeNameException("Nome do participante é muito grande = " + participantCompleteName);
			}
		}

		paragraph.add(participantCompleteName);

		return paragraph;
	}
}
