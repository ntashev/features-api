package com.task.features.persistence.repository;

import com.task.features.persistence.entity.FeatureEntity;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

/**
 * Repository for {@link FeatureEntity} persistence operations.
 */
public interface FeatureRepo extends Repository<FeatureEntity, Integer> {

    /**
     * Finds {@link FeatureEntity} by id.
     *
     * @param id id
     * @return Optional containing {@link FeatureEntity} if found, otherwise empty {@link Optional}
     */
    Optional<FeatureEntity> findOneById(Integer id);

    /**
     * Constructs a {@link Stream} of all {@link FeatureEntity}.
     *
     * @return stream of entities
     */
    Stream<FeatureEntity> findAll();

    /**
     * Saves {@link FeatureEntity} to persistence store.
     *
     * @param entity entity to save
     * @return saved entity
     */
    @Transactional
    FeatureEntity save(FeatureEntity entity);

    /**
     * Deletes {@link FeatureEntity} from persistence store.
     *
     * @param entity entity to delete
     */
    @Transactional
    void delete(FeatureEntity entity);
}
