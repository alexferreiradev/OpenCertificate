package dev.gojava.module.certificado.api;

import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import dev.gojava.module.certificado.service.CertificadoService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/certificates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CertificadoRest implements CertificadoApi {

    @Inject
    Logger logger;
    @Inject
    CertificadoService service;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response salvarListaCertificado(@MultipartForm GenerateCertForm form) {
        logger.info("Api de salvar certificado com params: {} ", form);
        CertificadoCommand certificadoCommand = CertificadoCommand.fromForm(form);
        CertificadoGeradoDTO certificadoGeradoDTO = service.criarListaCertificado(certificadoCommand);
        logger.info("Retorno da api com zip de tamanho: {}", certificadoGeradoDTO.tamanhoZIP);

        return Response.ok(certificadoGeradoDTO).status(Response.Status.CREATED).build();
    }

}
