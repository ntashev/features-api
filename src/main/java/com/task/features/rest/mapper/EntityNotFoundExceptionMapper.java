package com.task.features.rest.mapper;

import com.task.features.rest.dto.ErrorDto;
import com.task.features.service.exception.EntityNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link EntityNotFoundException}.
 */
@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(exception.getMessage())).build();
    }
}
