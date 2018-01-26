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
     * Enables/disables a features for a user.
     *
     * @param userId user id
     * @param featureId features id
     * @param enabled enabled/disabled
     */
    void updateFeatureForUser(Integer userId, Integer featureId, boolean enabled);
}
