package com.task.features.service;

import com.task.features.service.model.UserBo;

/**
 * User related business logic service.
 */
public interface UserService {

    /**
     * Gets user by id.
     *
     * @param userId user id
     * @return user bo
     */
    UserBo getUserById(Integer userId);

    /**
     * Enables/disables a feature for a user.
     *
     * @param userId user id
     * @param featureId feature id
     * @param enabled enabled/disabled
     */
    void updateFeatureForUser(Integer userId, Integer featureId, boolean enabled);
}
