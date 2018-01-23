package com.task.features.service;

import com.task.features.persistence.entity.UserBoToUserFeatureEntity;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.persistence.repository.UserRepo;
import com.task.features.service.model.FeatureBo;
import com.task.features.service.model.UserBo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserBo getUserById(Integer userId) {
        UserEntity user = repo.findOneById(userId);
        return UserBoToUserFeatureEntity.toBo(user);
    }

    @Override
    public void updateFeaturesForUser(Integer userId, Set<FeatureBo> features) {

    }

    @Override
    public void deleteFeatureForUser(Integer userId, FeatureBo feature) {

    }
}
