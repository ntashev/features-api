package com.task.features.service;

import com.task.features.service.model.FeatureBo;

import java.util.Set;

/**
 * Feature related business logic service.
 */
public interface FeatureService {

    /**
     * Gets all globally enabled features.
     *
     * @return set of all globally enabled features
     */
    Set<FeatureBo> getEnabledFeatures();

    /**
     * Updates a feature.
     *
     * @param featureId feature id
     * @param feature feature to update to
     */
    void updateFeature(Integer featureId, FeatureBo feature);

    /**
     * Creates a feature.
     *
     * @param feature feature to create
     * @return id of created feature
     */
    Integer createFeature(FeatureBo feature);

    /**
     * Deletes a feature.
     *
     * @param featureId feature id to delete
     */
    void deleteFeature(Integer featureId);
}
