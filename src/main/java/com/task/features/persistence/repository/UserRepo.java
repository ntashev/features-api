package com.task.features.persistence.repository;

import com.task.features.persistence.entity.UserEntity;

import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public interface UserRepo extends Repository<UserEntity, Integer> {

    UserEntity findOneById(Integer id);

    UserEntity save(UserEntity entity);

    void delete(UserEntity entity);
}
