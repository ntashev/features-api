package com.task.features.rest.config;

import com.task.features.rest.controller.FeaturesController;
import com.task.features.rest.mapper.ConstraintViolationExceptionMapper;
import com.task.features.rest.mapper.EntityNotFoundExceptionMapper;
import com.task.features.rest.mapper.ExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS related configuration.
 */
@ApplicationPath("api/v1/")
public class RestConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(FeaturesController.class,
                EntityNotFoundExceptionMapper.class, ConstraintViolationExceptionMapper.class, ExceptionMapper.class));
    }

}
