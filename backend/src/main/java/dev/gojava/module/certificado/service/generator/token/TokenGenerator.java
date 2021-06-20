package dev.gojava.module.certificado.service.generator.token;

import dev.gojava.module.certificado.model.Certificate;

public interface TokenGenerator {

	String generateToken(Certificate certificate);

}
