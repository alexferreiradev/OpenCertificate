package dev.gojava.core.util;

import dev.gojava.module.certificado.model.Participant;

import java.util.StringTokenizer;

public final class ParticipantUtil {

    public static String participantCompleteName(Participant participant) {
        return participant.getName() + " " + participant.getLastName();
    }

    /**
     * Cria texto de identificação de participante.
     *
     * @param participant dados do participante.
     * @return texto criado.
     */
    public static String createIdentifier(Participant participant) {
        String cpf = participant.getCpf();
        String rg = participant.getRg();
        return cpf != null ? cpf : rg;
    }

    public static String participantCompleteNameSummarized(Participant participant) {
        String lastNameSumarized = sumarizeLastName(participant.getLastName());
        return participant.getName() + " " + lastNameSumarized;
    }

    private static String sumarizeLastName(String lastName) {
        StringTokenizer tokenizer = new StringTokenizer(lastName, " ", false);
        int totalToken = tokenizer.countTokens();
        StringBuffer buffer = new StringBuffer();
        while (tokenizer.hasMoreTokens()) {
            int countTokens = tokenizer.countTokens();
            String token = tokenizer.nextToken();
            if (countTokens == 1) {
                buffer.append(token).append(" ");
            } else {
                buffer.append(token.charAt(0)).append(". ");
            }
        }

        return buffer.toString().trim();
    }
}
