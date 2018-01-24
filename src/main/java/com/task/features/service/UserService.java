package com.task.features.service;

import com.task.features.service.model.FeatureBo;
import com.task.features.service.model.UserBo;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface UserService {

    UserBo getUserById(Integer userId);

    void updateFeatureForUser(Integer userId, Integer featureId, boolean enabled);
}
