package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.dto.GenerateCertSpringForm;
import dev.gojava.module.certificado.service.CertificadoService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@ApplicationScoped
@Path("/certificados")
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(path = "/certificados")
public class CertificadoRest implements CertificadoApi {

    @Inject
    Logger logger;
    @Inject
    CertificadoService service;

    public CertificadoRest(Logger logger, CertificadoService service) {
        this.logger = logger;
        this.service = service;
    }

    /**
     * Create to quarkus version.
     *
     * @param form fomr.
     * @return certificate response
     */
    @POST
    @Consumes(javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response salvarListaCertificado(@MultipartForm GenerateCertForm form) {
        logger.info("Api de salvar certificado com params: {} ", form);
        CertificadoCommand certificadoCommand = CertificadoCommand.fromForm(form);
        CertificadoGeradoDTO certificadoGeradoDTO = service.criarListaCertificado(certificadoCommand);
        logger.info("Retorno da api com zip de tamanho: {}", certificadoGeradoDTO.tamanhoZIP);

        return Response.ok(certificadoGeradoDTO).status(Response.Status.CREATED).build();
    }

    /**
     * Create to spring version.
     *
     * @param form fomr.
     * @return certificate response
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Dto> salvarListaCertificadoSpring(@ModelAttribute GenerateCertSpringForm form) throws IOException {
        logger.info("Api de salvar certificado com params: {} ", form);
        GenerateCertForm genericForm = new GenerateCertForm();
        File tempFile = Files.createTempFile(genericForm.entityName, UUID.randomUUID().toString()).toFile();
        Files.copy(form.getFile().getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        genericForm.csvFile = tempFile;
        genericForm.entityName = form.getEntityName();
        Response response = salvarListaCertificado(genericForm);
        CertificadoGeradoDTO entity = response.readEntity(CertificadoGeradoDTO.class);
        Dto dto = new Dto();
        dto.entity = form.getEntityName();
        dto.zipFile = entity.arquivoZIP.getAbsolutePath();

        return ResponseEntity.status(response.getStatus()).body(dto);
    }

    @GetMapping(path = "/zipfiles", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getZip(@RequestParam("filePath") String zipFilePath) {
        return service.getZip(zipFilePath);
    }

    public class Dto {
        public String entity;
        public String zipFile;
    }
}
