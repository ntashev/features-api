package com.task.features.service;

import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.entity.UserEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.persistence.repository.UserRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.FeatureBo;
import com.task.features.service.model.UserBo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class UserServiceImplTest {

    private UserServiceImpl service;
    private FeatureRepo featureRepo;
    private UserRepo userRepo;

    @Before
    public void setup() {
        featureRepo = mock(FeatureRepo.class);
        userRepo = mock(UserRepo.class);
        service = new UserServiceImpl();
        service.setFeatureRepo(featureRepo);
        service.setUserRepo(userRepo);
    }

    @Test
    public void testGetUserByIdWithTwoEnabledFeatures() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("feature");
        e1.setGloballyEnabled(true);
        e2.setName("feature2");
        e2.setGloballyEnabled(true);
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(new HashSet<>(Arrays.asList(e1, e2)));
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        FeatureBo bo1 = new FeatureBo();
        FeatureBo bo2 = new FeatureBo();
        bo1.setName("feature");
        bo1.setEnabled(true);
        bo2.setName("feature2");
        bo2.setEnabled(true);
        Set<FeatureBo> expectedFeatures = new HashSet<>(Arrays.asList(bo1, bo2));
        UserBo expectedUser = new UserBo();
        expectedUser.setFeatures(expectedFeatures);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Smith");

        UserBo result = service.getUserById(1);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUserByIdWithOneEnabledAndOneDisabledFeatures() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("feature");
        e1.setGloballyEnabled(true);
        e2.setName("feature2");
        e2.setGloballyEnabled(false);
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(new HashSet<>(Arrays.asList(e1, e2)));
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        FeatureBo bo1 = new FeatureBo();
        bo1.setName("feature");
        bo1.setEnabled(true);
        Set<FeatureBo> expectedFeatures = new HashSet<>(Arrays.asList(bo1));
        UserBo expectedUser = new UserBo();
        expectedUser.setFeatures(expectedFeatures);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Smith");

        UserBo result = service.getUserById(1);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUserByIdWithTwoDisabledFeatures() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("feature");
        e1.setGloballyEnabled(false);
        e2.setName("feature2");
        e2.setGloballyEnabled(false);
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(new HashSet<>(Arrays.asList(e1, e2)));
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        FeatureBo bo1 = new FeatureBo();
        bo1.setName("feature");
        bo1.setEnabled(true);
        Set<FeatureBo> expectedFeatures = Collections.emptySet();
        UserBo expectedUser = new UserBo();
        expectedUser.setFeatures(expectedFeatures);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Smith");

        UserBo result = service.getUserById(1);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUserByIdWithNoFeatures() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(Collections.emptySet());
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        FeatureBo bo1 = new FeatureBo();
        bo1.setName("feature");
        bo1.setEnabled(true);
        Set<FeatureBo> expectedFeatures = Collections.emptySet();
        UserBo expectedUser = new UserBo();
        expectedUser.setFeatures(expectedFeatures);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Smith");

        UserBo result = service.getUserById(1);

        assertEquals(expectedUser, result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetUserByIdNotFound() {
        when(userRepo.findOneById(1)).thenReturn(Optional.empty());

        service.getUserById(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdateFeatureForUserFeatureNotFound() {
        when(userRepo.findOneById(1)).thenReturn(Optional.of(new UserEntity()));
        when(featureRepo.findOneById(1)).thenReturn(Optional.empty());

        service.updateFeatureForUser(1, 1, true);
    }

    @Test
    public void testUpdateFeatureForUserEnable() {
        FeatureEntity e1 = new FeatureEntity();
        e1.setName("feature");
        e1.setGloballyEnabled(true);
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(new HashSet<>(Arrays.asList(e1)));
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("test");
        e1.setGloballyEnabled(true);
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        when(featureRepo.findOneById(1)).thenReturn(Optional.of(e2));

        service.updateFeatureForUser(1, 1, true);

        verify(featureRepo).save(e2);
        verify(userRepo).save(user);
    }

    @Test
    public void testUpdateFeatureForUserDisable() {
        FeatureEntity e1 = new FeatureEntity();
        e1.setName("feature");
        e1.setGloballyEnabled(true);
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFeatures(new HashSet<>(Arrays.asList(e1)));
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("test");
        e1.setGloballyEnabled(true);
        when(userRepo.findOneById(1)).thenReturn(Optional.of(user));
        when(featureRepo.findOneById(1)).thenReturn(Optional.of(e2));

        service.updateFeatureForUser(1, 1, false);

        verify(featureRepo).save(e2);
        verify(userRepo).save(user);
    }
}
