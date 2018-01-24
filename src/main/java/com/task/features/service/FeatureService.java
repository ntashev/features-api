package com.task.features.service;

import com.task.features.service.model.FeatureBo;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface FeatureService {

    Set<FeatureBo> getEnabledFeatures();

    void updateFeature(Integer featureId, FeatureBo feature);

    void deleteFeature(Integer featureId);
}
