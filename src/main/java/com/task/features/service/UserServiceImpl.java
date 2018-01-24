package com.task.features.service;

import com.task.features.persistence.entity.UserBoToUserFeatureEntity;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.persistence.entity.UserFeatureEntity;
import com.task.features.persistence.repository.UserRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.FeatureBo;
import com.task.features.service.model.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
        UserEntity user =  repo.findOneById(userId).orElseThrow(() -> new EntityNotFoundException("User not found."));
        return UserBoToUserFeatureEntity.toBo(user);
    }

    @Override
    public void updateFeaturesForUser(Integer userId, Set<FeatureBo> features) {

    }

    @Override
    public void deleteFeatureForUser(Integer userId, Integer featureId) {
        UserEntity user =  repo.findOneById(userId).orElseThrow(() -> new EntityNotFoundException("User not found."));
        boolean isFeatureFound = false;
        for (Iterator<UserFeatureEntity> i = user.getUserFeatures().iterator(); i.hasNext();) {
            UserFeatureEntity ufe = i.next();
            if (ufe.getFeature().getId().equals(featureId)) {
                i.remove();
                isFeatureFound = true;
                break;
            }
        }

        if (!isFeatureFound) {
            new EntityNotFoundException("User does not have this feaure.");
        }

        repo.save(user);
    }
}
