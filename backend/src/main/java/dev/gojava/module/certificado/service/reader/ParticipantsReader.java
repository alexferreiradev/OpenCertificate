package dev.gojava.module.certificado.service.reader;

import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.model.Participant;

import java.util.List;

public interface ParticipantsReader {

	List<Participant> readParticipant(ReaderCommand command);
}
