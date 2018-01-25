package com.task.features.service.model;

import com.task.features.persistence.entity.FeatureBoToFeatureEntityFactory;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.service.model.UserBo;

import java.util.stream.Collectors;

/**
 * Factory to create {@link UserBo}.
 */
public class UserBoFactory {

    /**
     * Creates user bo from user entity.
     *
     * @param entity user entity
     * @return user bo
     */
    public static UserBo toBo(UserEntity entity) {
        UserBo bo = new UserBo();
        bo.setFirstName(entity.getFirstName());
        bo.setLastName(entity.getLastName());
        bo.setFeatures(entity.getFeatures().stream().map(e -> FeatureBoToFeatureEntityFactory.toBo(e, true)).collect(Collectors.toSet()));

        return bo;
    }
}
