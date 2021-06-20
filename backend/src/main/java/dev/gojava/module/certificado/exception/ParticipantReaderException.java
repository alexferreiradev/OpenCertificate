package dev.gojava.module.certificado.exception;

public class ParticipantReaderException extends Exception {

    public ParticipantReaderException() {
    }

    public ParticipantReaderException(String message) {
        super(message);
    }

    public ParticipantReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantReaderException(Throwable cause) {
        super(cause);
    }

    public ParticipantReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
