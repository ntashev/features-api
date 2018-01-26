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
     * Updates a features.
     *
     * @param featureId features id
     * @param feature features to update to
     */
    void updateFeature(Integer featureId, FeatureBo feature);

    /**
     * Creates a features.
     *
     * @param feature features to create
     * @return id of created features
     */
    Integer createFeature(FeatureBo feature);

    /**
     * Deletes a features.
     *
     * @param featureId features id to delete
     */
    void deleteFeature(Integer featureId);
}
