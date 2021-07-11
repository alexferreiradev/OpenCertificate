package dev.gojava.module.certificado.api;

import dev.gojava.core.exception.mapper.ValidationExceptionMapper;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;
import dev.gojava.module.certificado.dto.GenerateCertForm;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.core.Response;


/**
 * Documentação para API rest de certificado.
 */
public interface CertificadoApi {

    @Operation(summary = "Salva uma lista de certificados importados de um csv", description = "Importa de CSV todos dados para criar certificados. Gera certificado em PDF, "
            + "salva as informações dos certificados e retorna todos certificados em pdf compactados em ZIP")
    @APIResponses({
            @APIResponse(
                    description = "Retorna objeto com certificados gerados em ZIP",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificadoGeradoDTO.class))), @APIResponse(description = "Erro ao "
            + "tentar importar CSV", responseCode = "422"), @APIResponse(description = "Erro ao tentar gerar certificados", responseCode = "400"), @APIResponse(description =
            "Erro na requisição, alguma validação falhou", responseCode = ValidationExceptionMapper.STATUS_ERROR_TXT)})
    Response salvarListaCertificado(@MultipartForm GenerateCertForm form);

}
