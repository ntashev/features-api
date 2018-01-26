package com.task.features.service;

import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.service.model.UserBoFactory;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.persistence.repository.UserRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.UserBo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureRepo featureRepo;

    @Override
    @Cacheable(cacheNames = "users", key = "#userId")
    public UserBo getUserById(Integer userId) {
        UserEntity user =  userRepo.findOneById(userId).orElseThrow(() -> {
            logger.info("User with id {} not found", userId);
            return new EntityNotFoundException("User not found.");
        });

        logger.info("Found user with id {}", user.getId());
        //filter out services that are globally disabled
        user.setFeatures(user.getFeatures().stream().filter(FeatureEntity::isGloballyEnabled).collect(Collectors.toSet()));
        return UserBoFactory.toBo(user);
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#userId")
    public void updateFeatureForUser(Integer userId, Integer featureId, boolean enabled) {
        UserEntity user =  userRepo.findOneById(userId).orElseThrow(() -> {
            logger.info("User with id {} not found", userId);
            return new EntityNotFoundException("User not found.");
        });

        FeatureEntity feature = featureRepo.findOneById(featureId).orElseThrow(() -> {
            logger.info("Feature with id {} not found", featureId);
            return new EntityNotFoundException("Feature not found.");
        });

        if (enabled) {
            enableFeatureForUser(user, feature);
            logger.info("Enabled features with id {} for user with id {}", featureId, userId);
        } else {
            disableFeatureForUser(user, feature);
            logger.info("Disabled features with id {} for user with id {}", featureId, userId);
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

    //used for testing
    void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //used for testing
    void setFeatureRepo(FeatureRepo featureRepo) {
        this.featureRepo = featureRepo;
    }
}
