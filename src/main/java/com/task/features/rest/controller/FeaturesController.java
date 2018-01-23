package com.task.features.rest.controller;

import com.task.features.rest.dto.FeatureDto;
import com.task.features.rest.dto.UserFeaturesDto;
import com.task.features.service.FeatureService;
import com.task.features.service.UserService;
import com.task.features.service.model.FeatureBoToFeatureDtoFactory;
import com.task.features.service.model.UserBo;
import com.task.features.service.model.UserBoToUserFeatureDto;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author nikolay.tashev on 22/01/2018.
 */
@Path("/features")
public class FeaturesController {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<FeatureDto> getGlobalFeatures() {
        return featureService.getFeatures().stream().map(FeatureBoToFeatureDtoFactory::toDto).collect(Collectors.toSet());
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserFeaturesDto getFeaturesForUser(@PathParam("id") int id) {
        UserBo user = userService.getUserById(id);
        return UserBoToUserFeatureDto.toDto(user);
    }
}
