package dev.gojava.core.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import dev.gojava.core.helper.DateHelper;
import dev.gojava.core.helper.FileHelper;
import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

public final class GeneratorUtil {

    /**
     * Adiciona em um pdf um background como imagem.
     *
     * @param background pdf
     * @param command objeto com dados uteis.
     * @throws IOException em caso de erro de leitura de arquivo.
     * @throws URISyntaxException em caso de URI de arquivo invalido.
     * @throws DocumentException em caso de não poder adicionar imagem no documento pdf.
     */
    public static void addBackgroundImage(PdfContentByte background, CertificatorGeneratorCommand command) throws IOException, URISyntaxException, DocumentException {
        String backgroundFileName = command.getBackgroundFileName();

        URL backgroundUrl = GeneratorUtil.class.getResource("/img/gojava_certificado-2-0-0.png").toURI().toURL();
        if (FileHelper.isValidFile(backgroundFileName)) {
            backgroundUrl = new File(backgroundFileName).toURI().toURL();
        }

        Image image = Image.getInstance(backgroundUrl);
        image.setAbsolutePosition(0, 0);
        image.scaleAbsolute(PageSize.A4.rotate());
        background.saveState();
        PdfGState pdfGState = new PdfGState();
        pdfGState.setFillOpacity(0.8f);
        background.setGState(pdfGState);
        background.addImage(image);
        background.restoreState();
    }

    /**
     * Cria font para certificado.
     *
     * @return font padrão para certificado.
     */
    public static Font createFontToCertText() {
        Font paragraphFont = new Font();
        paragraphFont.setSize(24);
        paragraphFont.setColor(BaseColor.WHITE);
        paragraphFont.setFamily(BaseFont.TIMES_ROMAN);
        paragraphFont.setStyle(Font.NORMAL);

        return paragraphFont;
    }

    /**
     * Gera texto padrão para certificado GoJava.
     *
     * @param certificate dados do certificado.
     * @return texto criado com dados do certificado.
     */
    public static String generateCertificateText(Certificate certificate) {
        String finalText = "Certificamos que NOME_PARTICIPANTE com TIPO_IDENTIFICACAO IDENTIFICACAO_PARTICIPANTE participou do evento NOME_EVENTO durante HORAS_EVENTO horas no "
                + "dia DATA_EVENTO promovido pelo Gojava - Grupo de usuários Java de Goiás. O evento foi sobre ASSUNTO_EVENTO. Valide seu certificado em gojava.dev com o token "
                + "de validação: VALIDATOR_UUID";
        Participant participant = certificate.getParticipant();
        finalText = finalText.replaceAll("NOME_PARTICIPANTE", ParticipantUtil.participantCompleteName(participant).toUpperCase());
        if (participant.getCpf() != null) {
            finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "CPF");
            finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getCpf());
        } else {
            finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "RG");
            finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getRg());
        }
        finalText = finalText.replaceAll("NOME_EVENTO", participant.getEvent().getName());
        finalText = finalText.replaceAll("HORAS_EVENTO", participant.getHour());

        Date dateStarted = participant.getEvent().getDateStarted();
        Date dateEnded = participant.getEvent().getDateEnded();
        if (dateEnded != null && !dateEnded.equals(dateStarted)) {
            String dateStartedString = DateHelper.format(dateStarted, DateFormat.SHORT);
            String dateEndedString = DateHelper.format(dateEnded, DateFormat.SHORT);
            finalText = finalText.replaceAll("DATA_EVENTO", String.format("%s - %s", dateStartedString, dateEndedString));
        } else {
            finalText = finalText.replaceAll("DATA_EVENTO", DateHelper.format(dateStarted, DateFormat.SHORT));
        }

        finalText = finalText.replaceAll("ASSUNTO_EVENTO", participant.getEvent().getTalkerTopics());
        finalText = finalText.replaceAll("VALIDATOR_UUID", certificate.getUuid());

        return finalText;
    }

    /**
     * Cria o nome para o arquivo do certificado com nome do participante.
     *
     * @param participant dados do participante.
     * @return nome do arquivo com o nome do participante tratado.
     */
    public static String createCertName(Participant participant) {
        String identy = participant.getCpf();
        if (identy == null || identy.isEmpty()) {
            identy = participant.getRg();
        }

        return "certificado_" + identy;
    }
}
