package dev.gojava.module.certificado.service;

import dev.gojava.module.certificado.command.CertificadoCommand;
import dev.gojava.module.certificado.dto.CertificadoGeradoDTO;

public interface CertificadoService {

    /**
     * Faz importação de CSV, cria todos dados para todos certificados,
     * gera todos certificados em PDF, salva os dados dos certificados em banco,
     * cria zip com todos PDFs e retorna o zip.
     *
     * @param command dados para serem importados
     * @return Zip com pdfs gerados
     */
    CertificadoGeradoDTO criarListaCertificado(CertificadoCommand command);
}
