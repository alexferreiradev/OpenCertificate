package dev.gojava.module.certificado.model.metadata;

import java.util.List;

public class JsonMetadata {
	private String IntegrityKey;
	private List<CertificateMetadata> certificates;

	public String getIntegrityKey() {
		return IntegrityKey;
	}

	public void setIntegrityKey(String integrityKey) {
		IntegrityKey = integrityKey;
	}

	public List<CertificateMetadata> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<CertificateMetadata> certificates) {
		this.certificates = certificates;
	}
}
