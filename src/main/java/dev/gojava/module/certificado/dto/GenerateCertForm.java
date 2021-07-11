package dev.gojava.module.certificado.dto;

import dev.gojava.module.certificado.validation.EntityNameValid;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.File;

public class GenerateCertForm {

    @FormParam("entityName")
    @PartType(MediaType.TEXT_PLAIN)
    @EntityNameValid
    public String entityName;

    @FormParam("csv")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    @NotNull(message = "Deve passar um csv")
    public File csvFile;

    @Override
    public String toString() {
        return "GenerateCertForm{" + "entityName='" + entityName + '\'' + ", csvFile=" + csvFile + '}';
    }
}
