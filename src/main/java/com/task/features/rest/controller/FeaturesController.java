package com.task.features.rest.controller;

import com.task.features.rest.dto.FeatureDto;
import com.task.features.rest.dto.FeatureDtoFactory;
import com.task.features.service.FeatureService;
import com.task.features.service.UserService;
import com.task.features.service.model.UserBo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

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
        return featureService.getFeatures().stream().map(FeatureDtoFactory::toDto).collect(Collectors.toSet());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGlobalFeature(@PathParam("id") int id) {
        featureService.deleteFeature(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<FeatureDto> getFeaturesForUser(@PathParam("id") int id) {
        UserBo user = userService.getUserById(id);
        return user.getFeatures().stream().map(FeatureDtoFactory::toDto).collect(Collectors.toSet());
    }

    @DELETE
    @Path("/user/{userId}/feature/{featureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFeatureForUser(@PathParam("userId") int userId, @PathParam("featureId") int featureId) {
        userService.deleteFeatureForUser(userId, featureId);
        return Response.noContent().build();
    }
}
