package dev.gojava.core.helper.zip;

public class ZipFileNotCreatedException extends Exception {
    public ZipFileNotCreatedException(String message) {
        super(message);
    }

    public ZipFileNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipFileNotCreatedException(Throwable cause) {
        super(cause);
    }
}
