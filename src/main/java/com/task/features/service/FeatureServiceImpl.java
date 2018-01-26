package com.task.features.service;

import com.task.features.persistence.entity.FeatureBoToFeatureEntityFactory;
import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.FeatureBo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    public static final String ALL_FEATURES_KEY = "allFeaturesKey";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeatureRepo repo;

    @Override
    @Cacheable(cacheNames = "features", key = "#root.target.ALL_FEATURES_KEY")
    public Set<FeatureBo> getEnabledFeatures() {
        try (Stream<FeatureEntity> features = repo.findAll()) {
            return features.filter(FeatureEntity::isGloballyEnabled).map(FeatureBoToFeatureEntityFactory::toBo).collect(Collectors.toSet());
        }
    }

    @Override
    @CacheEvict(cacheNames = {"features", "users"}, allEntries =  true)
    public void updateFeature(Integer featureId, FeatureBo feature) {
        FeatureEntity existingFeature = repo.findOneById(featureId).orElseThrow(() -> {
            logger.info("Feature with id {} not found", featureId);
            return new EntityNotFoundException("Feature not found.");
        });

        existingFeature.setGloballyEnabled(feature.isEnabled());
        existingFeature.setName(feature.getName());
        repo.save(existingFeature);

        logger.info("Updated features with id {}", featureId);
    }

    @Override
    @CacheEvict(cacheNames = "features", key = "#root.target.ALL_FEATURES_KEY")
    public Integer createFeature(FeatureBo feature) {
        FeatureEntity entity = repo.save(FeatureBoToFeatureEntityFactory.toEntity(feature));
        logger.info("Created features with id {}", entity.getId());
        return entity.getId();
    }

    @Override
    @CacheEvict(cacheNames = {"features", "users"}, allEntries =  true)
    public void deleteFeature(Integer featureId) {
        repo.findOneById(featureId).ifPresent(repo::delete);
        logger.info("Deleted features with id {}", featureId);
    }

    //for testing
    void setRepo(FeatureRepo repo) {
        this.repo = repo;
    }
}
