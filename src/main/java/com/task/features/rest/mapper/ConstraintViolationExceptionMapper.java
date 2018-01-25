package com.task.features.rest.mapper;

import com.task.features.rest.dto.ErrorDto;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Exception mapper for {@link ConstraintViolationException}.
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violationSet = exception.getConstraintViolations();
        StringBuilder errorMessage = new StringBuilder();
        boolean isFirst = true;
        for (ConstraintViolation<?> c : violationSet) {
            if (isFirst) {
                isFirst = false;
            } else {
                errorMessage.append(", ");
            }

            errorMessage.append("'");
            errorMessage.append(getPropertyName(c.getPropertyPath()));
            errorMessage.append("' ");
            errorMessage.append(c.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(errorMessage.toString())).build();
    }

    private String getPropertyName(Path path) {
        Iterator<Path.Node> i = path.iterator();

        Path.Node last = null;
        while (i.hasNext()) {
            last = i.next();
        }

        if (last != null) {
            return last.toString();
        } else {
            return "unknown property";
        }
    }
}
