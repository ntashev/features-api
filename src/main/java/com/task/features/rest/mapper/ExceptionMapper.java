package com.task.features.rest.mapper;

import com.task.features.rest.dto.ErrorDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Exception mapper for {@link Exception}.
 */
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong with this request. Try again later.";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response toResponse(Exception exception) {
        logger.error("Internal Server Error {}", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(INTERNAL_SERVER_ERROR_MESSAGE)).build();
    }
}
