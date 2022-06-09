package dev.gojava.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import org.junit.jupiter.api.Test;

import dev.gojava.module.certificado.command.CertificatorGeneratorCommand;

public final class GeneratorUtilTest {

    /**
     * Testa se adicação de imagens de background ocorrem sem exceções quando os parâmetros estão corretos
     */
    @Test
    public void givenValidBackgroundFileName_WhenCallAddBackgroundImageMethod_ThenBackgroundImageAddedSuccefully() throws DocumentException, IOException, URISyntaxException{
        // try{
        // Assertions.assertAll(new Executable(){
        //     @Override
        //     public void execute() throws Throwable{
                URL pdfResourceURL = this.getClass().getClassLoader().getResource("img/gojava_certificado-2-0-0.pdf");
                URL pngResourceURL = this.getClass().getClassLoader().getResource("img/gojava_certificado-2-0-0.png");
                CertificatorGeneratorCommand certificatorGeneratorCommand = new CertificatorGeneratorCommand();
                certificatorGeneratorCommand.setBackgroundFileName(pngResourceURL.getFile());
                System.out.println(new File(pdfResourceURL.getFile()).exists());//true
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfResourceURL.getFile()));
                document.open();
                GeneratorUtil.addBackgroundImage(pdfWriter.getDirectContentUnder(), certificatorGeneratorCommand);
        //     }
        // });
        // }catch(Exception e){e.printStackTrace();}
    }

    // /**
    //  * Cria font para certificado.
    //  *
    //  * @return font padrão para certificado.
    //  */
    // public static Font createFontToCertText() {
    //     Font paragraphFont = new Font();
    //     paragraphFont.setSize(24);
    //     paragraphFont.setColor(BaseColor.WHITE);
    //     paragraphFont.setFamily(BaseFont.TIMES_ROMAN);
    //     paragraphFont.setStyle(Font.NORMAL);

    //     return paragraphFont;
    // }

    // /**
    //  * Gera texto padrão para certificado GoJava.
    //  *
    //  * @param certificate dados do certificado.
    //  * @return texto criado com dados do certificado.
    //  */
    // public static String generateCertificateText(Certificate certificate) {
    //     String finalText = "Certificamos que NOME_PARTICIPANTE com TIPO_IDENTIFICACAO IDENTIFICACAO_PARTICIPANTE participou do evento NOME_EVENTO durante HORAS_EVENTO horas no "
    //             + "dia DATA_EVENTO promovido pelo Gojava - Grupo de usuários Java de Goiás. O evento foi sobre ASSUNTO_EVENTO. Valide seu certificado em gojava.dev com o token "
    //             + "de validação: VALIDATOR_UUID";
    //     Participant participant = certificate.getParticipant();
    //     finalText = finalText.replaceAll("NOME_PARTICIPANTE", ParticipantUtil.participantCompleteName(participant).toUpperCase());
    //     if (participant.getCpf() != null) {
    //         finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "CPF");
    //         finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getCpf());
    //     } else {
    //         finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "RG");
    //         finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getRg());
    //     }
    //     finalText = finalText.replaceAll("NOME_EVENTO", participant.getEvent().getName());
    //     finalText = finalText.replaceAll("HORAS_EVENTO", participant.getHour());

    //     Date dateStarted = participant.getEvent().getDateStarted();
    //     Date dateEnded = participant.getEvent().getDateEnded();
    //     if (dateEnded != null && !dateEnded.equals(dateStarted)) {
    //         String dateStartedString = DateHelper.format(dateStarted, DateFormat.SHORT);
    //         String dateEndedString = DateHelper.format(dateEnded, DateFormat.SHORT);
    //         finalText = finalText.replaceAll("DATA_EVENTO", String.format("%s - %s", dateStartedString, dateEndedString));
    //     } else {
    //         finalText = finalText.replaceAll("DATA_EVENTO", DateHelper.format(dateStarted, DateFormat.SHORT));
    //     }

    //     finalText = finalText.replaceAll("ASSUNTO_EVENTO", participant.getEvent().getTalkerTopics());
    //     finalText = finalText.replaceAll("VALIDATOR_UUID", certificate.getUuid());

    //     return finalText;
    // }

    // /**
    //  * Cria o nome para o arquivo do certificado com nome do participante.
    //  *
    //  * @param participant dados do participante.
    //  * @return nome do arquivo com o nome do participante tratado.
    //  */
    // public static String createCertName(Participant participant) {
    //     String identy = participant.getCpf();
    //     if (identy == null || identy.isEmpty()) {
    //         identy = participant.getRg();
    //     }

    //     return "certificado_" + identy;
    // }
}
