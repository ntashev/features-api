package com.task.features.service;

import com.task.features.persistence.entity.FeatureBoToFeatureEntityFactory;
import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.service.model.FeatureBo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }
}
