package dev.gojava.module.certificado.service.generator;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacao para configurar o tipo de gerador de certificado.
 */
@Documented
@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CertificateGeneratorType {

    /**
     * Configura o tipo de instanciador de gerador de certificado.
     *
     * @return tipo configurado.
     */
    GeneratorType type() default GeneratorType.GOJAVA;
}
