package dev.gojava.module.certificado.model.metadata;

import java.util.List;

public class JsonMetadata {
    private String integrityKey;
    private List<CertificateMetadata> certificates;

    public String getIntegrityKey() {
        return integrityKey;
    }

    public void setIntegrityKey(String integrityKey) {
        this.integrityKey = integrityKey;
    }

    public List<CertificateMetadata> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateMetadata> certificates) {
        this.certificates = certificates;
    }
}
