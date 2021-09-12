package dev.gojava.module.certificado.validation.validator;

import dev.gojava.module.certificado.service.generator.CertificateGenerator;
import dev.gojava.module.certificado.service.generator.CertificateGeneratorType;
import dev.gojava.module.certificado.service.generator.GeneratorType;
import dev.gojava.module.certificado.validation.EntityNameValid;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@ApplicationScoped
public class EntityNameValidator implements ConstraintValidator<EntityNameValid, String> {

    @Inject
    Logger logger;
    @Inject
    Instance<CertificateGenerator> generators;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;

        return generators.stream().anyMatch(certificateGenerator -> {
            boolean annotationPresent = certificateGenerator.getClass().isAnnotationPresent(CertificateGeneratorType.class);
            if (!annotationPresent) {
                logger.warn("Gerador de certificado sem anotação de tipo: {}", certificateGenerator.getClass().getName());
                return false;
            }

            try {
                GeneratorType generatorType = GeneratorType.valueOf(value.toUpperCase());
                CertificateGeneratorType annotation = certificateGenerator.getClass().getAnnotation(CertificateGeneratorType.class);
                GeneratorType type = annotation.type();

                return type.equals(generatorType);
            } catch (IllegalArgumentException e) {
                logger.error("Tipo de gerador não foi encontrado", e);
                return false;
            }
        });
    }

}
