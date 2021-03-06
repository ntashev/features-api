package com.task.features.persistence.repository;

import com.task.features.persistence.entity.UserEntity;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Repository for {@link UserEntity} persistence operations.
 */
public interface UserRepo extends Repository<UserEntity, Integer> {

    /**
     * Finds {@link UserEntity} by id.
     *
     * @param id user id
     * @return Optional containing {@link  UserEntity} if found, otherwise empty {@link Optional}
     */
    Optional<UserEntity> findOneById(Integer id);

    /**
     * Saves {@link UserEntity} to persistence store.
     *
     * @param entity entity to save
     */
    @Transactional
    void save(UserEntity entity);

    /**
     * Deletes {@link UserEntity} from persistence store.
     *
     * @param entity entity to delete
     */
    @Transactional
    void delete(UserEntity entity);
}
