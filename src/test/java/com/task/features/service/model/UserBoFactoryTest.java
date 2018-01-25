package com.task.features.service.model;

import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.entity.UserEntity;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class UserBoFactoryTest {

    @Test
    public void testToBo() {
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

        UserBo bo = UserBoFactory.toBo(user);

        assertEquals(expectedUser, bo);
    }

    @Test
    public void testToBoEmptyFeatures() {
        UserEntity user = new UserEntity();
        user.setFirstName("John");
        user.setLastName("Smith");
        Set<FeatureBo> expectedFeatures = Collections.emptySet();
        UserBo expectedUser = new UserBo();
        expectedUser.setFeatures(expectedFeatures);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Smith");

        UserBo bo = UserBoFactory.toBo(user);

        assertEquals(expectedUser, bo);
    }
}
