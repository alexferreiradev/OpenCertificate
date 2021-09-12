package dev.gojava.module.certificado.command;

import dev.gojava.module.certificado.dto.GenerateCertForm;

public class CertificadoCommand {

    public GenerateCertForm form;

    /**
     * Cria command a partir de form.
     *
     * @param form formulario com dados.
     * @return command
     */
    public static CertificadoCommand fromForm(GenerateCertForm form) {
        CertificadoCommand command = new CertificadoCommand();
        command.form = form;

        return command;
    }

    @Override
    public String toString() {
        return "CertificadoCommand{}";
    }

    /**
     * Cria command reader a partir de command.
     *
     * @return command reader
     */
    public ReaderCommand getReaderCommand() {
        ReaderCommand readerCommand = new ReaderCommand();
        readerCommand.absoluteFilePath = form.csvFile.getAbsolutePath();

        return readerCommand;
    }
}
