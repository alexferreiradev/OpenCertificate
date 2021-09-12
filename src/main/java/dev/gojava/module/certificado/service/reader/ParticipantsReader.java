package dev.gojava.module.certificado.service.reader;

import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.model.Participant;

import java.io.File;
import java.util.List;

/**
 * Interface que define leitores de arquivos de certificados.
 *
 * @see CsvParticipantReader
 */
public interface ParticipantsReader {

    List<Participant> readParticipant(ReaderCommand command);

    ReaderCommand createReaderCommandOrThrow(File csvFile);
}
