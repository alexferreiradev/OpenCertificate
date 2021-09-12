package dev.gojava.module.certificado.command;

import dev.gojava.module.certificado.model.Participant;

import java.util.List;

public class CertificatorGeneratorCommand {

    private List<Participant> participantList;
    private String backgroundFileName;

    public static CertificatorGeneratorCommand from(List<Participant> participants, String backgroundFile) {
        CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();
        command.setParticipantList(participants);
        command.setBackgroundFileName(backgroundFile);

        return command;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    public String getBackgroundFileName() {
        return backgroundFileName;
    }

    public void setBackgroundFileName(String backgroundFileName) {
        this.backgroundFileName = backgroundFileName;
    }
}
