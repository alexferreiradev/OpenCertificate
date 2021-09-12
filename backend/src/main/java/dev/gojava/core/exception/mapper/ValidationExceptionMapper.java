package dev.gojava.core.exception.mapper;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    public static final String STATUS_ERROR_TXT = "403";

    @Override
    public Response toResponse(ValidationException e) {
        return Response.status(statusInt()).entity(e.getMessage()).build();
    }

    private int statusInt() {
        return Integer.parseInt(ValidationExceptionMapper.STATUS_ERROR_TXT);
    }
}
