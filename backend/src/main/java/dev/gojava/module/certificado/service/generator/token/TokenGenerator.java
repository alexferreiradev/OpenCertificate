package dev.gojava.module.certificado.service.generator.token;

import dev.gojava.module.certificado.model.Certificate;

/**
 * Interface que define geradores de token para certificados.
 *
 * @see TokenGenerator_SHA256
 */
public interface TokenGenerator {

    String generateToken(Certificate certificate);

}
