package com.task.features.persistence.repository;

import com.task.features.persistence.entity.FeatureEntity;

import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface FeatureRepo extends Repository<FeatureEntity, Integer> {

    Stream<FeatureEntity> findAll();

    FeatureEntity save(FeatureEntity entity);

    void delete(FeatureEntity entity);
}
