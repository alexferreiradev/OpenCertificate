package dev.gojava.common.fixture;

import dev.gojava.module.certificado.model.Certificate;

public interface CertificateFixture {

    static Certificate create() {
        return new Certificate();
    }
}
