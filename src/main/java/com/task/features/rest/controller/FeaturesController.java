package com.task.features.rest.controller;

import com.task.features.rest.dto.AvailableFeaturesDto;
import com.task.features.rest.dto.AvailableFeaturesDtoFactory;
import com.task.features.rest.dto.FeatureForUserRequestDto;
import com.task.features.rest.dto.FeatureRequestDto;
import com.task.features.service.FeatureService;
import com.task.features.service.UserService;
import com.task.features.service.model.FeatureBoFactory;
import com.task.features.service.model.UserBo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

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
    public AvailableFeaturesDto getGlobalFeatures() {
        return AvailableFeaturesDtoFactory.toDto(featureService.getEnabledFeatures());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFeature(FeatureRequestDto feature) throws URISyntaxException {
        //validation
        Integer id = featureService.createFeature(FeatureBoFactory.toBo(feature));
        return Response.created(new URI("features/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeature(@PathParam("id") int id, FeatureRequestDto feature) {
        //validation
        featureService.updateFeature(id, FeatureBoFactory.toBo(feature));
        return Response.noContent().build();
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
    public AvailableFeaturesDto getFeaturesForUser(@PathParam("id") int id) {
        UserBo user = userService.getUserById(id);
        return AvailableFeaturesDtoFactory.toDto(user.getFeatures());
    }

    @PUT
    @Path("/user/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enableFeatureForUser(@PathParam("userId") int userId, FeatureForUserRequestDto request) {
        //validation
        userService.updateFeatureForUser(userId, request.getFeatureId(), request.isEnabled());
        return Response.noContent().build();
    }

}
