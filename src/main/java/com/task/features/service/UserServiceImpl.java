package com.task.features.service;

import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.entity.UserBoToUserFeatureEntity;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.persistence.repository.UserRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureRepo featureRepo;

    @Override
    public UserBo getUserById(Integer userId) {
        UserEntity user =  userRepo.findOneById(userId).orElseThrow(() -> new EntityNotFoundException("User not found."));
        //filter out services that are globally disabled
        user.setFeatures(user.getFeatures().stream().filter(f -> f.isGloballyEnabled()).collect(Collectors.toSet()));
        return UserBoToUserFeatureEntity.toBo(user);
    }

    @Override
    public void updateFeatureForUser(Integer userId, Integer featureId, boolean enabled) {
        UserEntity user =  userRepo.findOneById(userId).orElseThrow(() -> new EntityNotFoundException("User not found."));
        FeatureEntity feature = featureRepo.findOneById(featureId).orElseThrow(() -> new EntityNotFoundException("Feature not found."));
        if (enabled) {
            enableFeatureForUser(user, feature);
        } else {
            disableFeatureForUser(user, feature);
        }
    }

    private void enableFeatureForUser(UserEntity user, FeatureEntity feature) {
        user.getFeatures().add(feature);
        feature.getUsers().add(user);
        featureRepo.save(feature);
        userRepo.save(user);
    }

    private void disableFeatureForUser(UserEntity user, FeatureEntity feature) {
        user.getFeatures().remove(feature);
        feature.getUsers().remove(user);
        userRepo.save(user);
        featureRepo.save(feature);
    }
}
