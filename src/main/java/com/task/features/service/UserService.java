package com.task.features.service;

import com.task.features.service.model.FeatureBo;
import com.task.features.service.model.UserBo;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface UserService {

    UserBo getUserById(Integer userId);

    void updateFeaturesForUser(Integer userId, Set<FeatureBo> features);

    void deleteFeatureForUser(Integer userId, FeatureBo feature);
}
