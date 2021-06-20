package dev.gojava.module.certificado.exception;

public class LargeNameException extends Exception {

    public LargeNameException() {
    }

    public LargeNameException(String message) {
        super(message);
    }

    public LargeNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public LargeNameException(Throwable cause) {
        super(cause);
    }
}
