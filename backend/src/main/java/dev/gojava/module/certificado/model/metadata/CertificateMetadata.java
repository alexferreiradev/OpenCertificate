package dev.gojava.module.certificado.model.metadata;

public class CertificateMetadata {
    private String token;
    private ParticipantMetadata participant;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ParticipantMetadata getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantMetadata participant) {
        this.participant = participant;
    }
}
