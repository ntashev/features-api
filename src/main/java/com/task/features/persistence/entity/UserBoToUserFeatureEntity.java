package com.task.features.persistence.entity;

import com.task.features.service.model.UserBo;

import java.util.stream.Collectors;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class UserBoToUserFeatureEntity {

    public static UserBo toBo(UserEntity entity) {
        UserBo bo = new UserBo();
        bo.setId(entity.getId());
        bo.setFirstName(entity.getFirstName());
        bo.setLastName(entity.getLastName());
        bo.setFeatures(entity.getFeatures().stream().map(e -> FeatureBoToFeatureEntityFactory.toBo(e, true)).collect(Collectors.toSet()));

        return bo;
    }
}
