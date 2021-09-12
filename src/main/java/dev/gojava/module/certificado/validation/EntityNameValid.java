package dev.gojava.module.certificado.validation;

import dev.gojava.module.certificado.validation.validator.EntityNameValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Valida o campo entityName de um form usado pela api de geraçao de certificado.
 * Valida se existe gerador de certificado para a entidade solicitada.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EntityNameValidator.class)
public @interface EntityNameValid {

    /**
     * Mensagem de erro.
     *
     * @return - mensagem
     */
    String message() default "Esta entidade não tem gerador de certificado configurado";

}
