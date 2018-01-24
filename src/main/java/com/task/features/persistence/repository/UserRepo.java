package com.task.features.persistence.repository;

import com.task.features.persistence.entity.UserEntity;

import org.springframework.data.repository.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface UserRepo extends Repository<UserEntity, Integer> {

    Optional<UserEntity> findOneById(Integer id);

    @Transactional
    UserEntity save(UserEntity entity);

    @Transactional
    void delete(UserEntity entity);
}
