package com.task.features.service;

import com.task.features.persistence.entity.FeatureBoToFeatureEntityFactory;
import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.entity.UserFeatureEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.FeatureBo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepo repo;

    @Override
    public Set<FeatureBo> getFeatures() {
        try (Stream<FeatureEntity> features = repo.findAll()) {
            return features.map(FeatureBoToFeatureEntityFactory::toBo).collect(Collectors.toSet());
        }
    }

    @Override
    public void updateFeature(Integer featureId, FeatureBo feature) {
    }

    @Override
    public void deleteFeature(Integer featureId) {
        FeatureEntity feature = repo.findOneById(featureId).orElseThrow(() -> new EntityNotFoundException("Feature not found."));
        for (Iterator<UserFeatureEntity> i = feature.getUserFeatures().iterator(); i.hasNext();) {
            UserFeatureEntity ufe = i.next();
            if (ufe.getFeature().getId().equals(featureId)) {
                i.remove();
                System.out.println("Removed " + ufe.getId());
            }
        }
        for (UserFeatureEntity e : feature.getUserFeatures()) {
            System.out.println(e.getId());
        }
        feature = repo.saveAndFlush(feature);
        System.out.println("updated!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        repo.delete(feature);
    }
}
