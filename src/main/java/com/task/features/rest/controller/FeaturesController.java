package com.task.features.rest.controller;

import com.task.features.rest.dto.AvailableFeaturesDto;
import com.task.features.rest.dto.AvailableFeaturesDtoFactory;
import com.task.features.rest.dto.FeatureForUserRequestDto;
import com.task.features.rest.dto.FeatureRequestDto;
import com.task.features.service.FeatureService;
import com.task.features.service.UserService;
import com.task.features.service.model.FeatureBoFactory;
import com.task.features.service.model.UserBo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Features functionality endpoints.
 */
@Path("/features")
public class FeaturesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeatureService featureService;

    @Autowired
    private UserService userService;

    /**
     * Gets enabled global features.
     *
     * @return global features with 200 status
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AvailableFeaturesDto getGlobalFeatures() {
        logger.info("Fetching all globally enabled features");
        return AvailableFeaturesDtoFactory.toDto(featureService.getEnabledFeatures());
    }

    /**
     * Creates a feature.
     *
     * @param feature feature dto
     * @return empty response with 201 status
     * @throws URISyntaxException in case of illigela URI
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFeature(@Valid FeatureRequestDto feature) throws URISyntaxException {
        logger.info("Creating feature {}", feature);
        Integer id = featureService.createFeature(FeatureBoFactory.toBo(feature));
        return Response.created(new URI("features/" + id)).build();
    }

    /**
     * Updates a feature.
     *
     * @param id feature id
     * @param feature updated feature
     * @return empty response with 204 status
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeature(@Valid @Min(1) @PathParam("id") int id, @Valid FeatureRequestDto feature) {
        logger.info("Updating feature with id {} to {}", id, feature);
        featureService.updateFeature(id, FeatureBoFactory.toBo(feature));
        return Response.noContent().build();
    }

    /**
     * Deletes a feature.
     *
     * @param id feature id
     * @return empty response with 204 status
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGlobalFeature(@Valid @Min(1) @PathParam("id") int id) {
        logger.info("Deleting feature with id {}", id);
        featureService.deleteFeature(id);
        return Response.noContent().build();
    }

    /**
     * Gets all enabled features for user.
     *
     * @param id user id
     * @return enabled features response with status 200
     */
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AvailableFeaturesDto getFeaturesForUser(@Valid @Min(1) @PathParam("id") int id) {
        logger.info("Getting enabled features for user with id {}", id);
        UserBo user = userService.getUserById(id);
        return AvailableFeaturesDtoFactory.toDto(user.getFeatures());
    }

    /**
     * Enables/disables a feature for user.
     *
     * @param userId user id
     * @param request feature to enable/disable
     * @return empty response with 204 status
     */
    @PUT
    @Path("/user/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enableFeatureForUser(@Valid @Min(1) @PathParam("userId") int userId, @Valid FeatureForUserRequestDto request) {
        logger.info("Updating feature with id {} for user with id {} to enabled {}", request.getFeatureId(), userId, request.isEnabled());
        userService.updateFeatureForUser(userId, request.getFeatureId(), request.isEnabled());
        return Response.noContent().build();
    }

}
