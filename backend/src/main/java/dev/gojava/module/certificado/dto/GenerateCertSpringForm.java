package dev.gojava.module.certificado.dto;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

public class GenerateCertSpringForm {
    private String entityName;
    private MultipartFile file;


    @Override
    public String toString() {
        return "GenerateCertForm{" + "entityName='" + getEntityName() + '\'' + ", csvFile=" + getFile().getOriginalFilename() + '}';
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
