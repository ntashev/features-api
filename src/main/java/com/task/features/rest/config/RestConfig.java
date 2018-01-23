package com.task.features.rest.config;

import com.task.features.rest.controller.FeaturesController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * @author nikolay.tashev on 22/01/2018.
 */
@ApplicationPath("api/v1/")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(FeaturesController.class));
    }
}
