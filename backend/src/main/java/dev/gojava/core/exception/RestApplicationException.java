package dev.gojava.core.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RestApplicationException extends WebApplicationException {

    public RestApplicationException(String message) {
        super(message);
    }

    public RestApplicationException(Response response) {
        super(response);
    }

    public RestApplicationException(String message, Response response) {
        super(message, response);
    }

    public RestApplicationException(int status) {
        super(status);
    }

    public RestApplicationException(String message, int status) {
        super(message, status);
    }

    public RestApplicationException(Response.Status status) {
        super(status);
    }

    public RestApplicationException(String message, Response.Status status) {
        super(message, status);
    }

    public RestApplicationException(Throwable cause) {
        super(cause);
    }

    public RestApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestApplicationException(Throwable cause, Response response) {
        super(cause, response);
    }

    public RestApplicationException(String message, Throwable cause, Response response) {
        super(message, cause, response);
    }

    public RestApplicationException(Throwable cause, int status) {
        super(cause, status);
    }

    public RestApplicationException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }

    public RestApplicationException(Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(cause, status);
    }

    public RestApplicationException(String message, Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(message, cause, status);
    }
}
