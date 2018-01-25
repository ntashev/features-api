package com.task.features.persistence.entity;

import com.task.features.service.model.FeatureBo;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class FeatureBoToFeatureEntityFactoryTest {

    @Test
    public void testToBoWithEnabled() {
        FeatureEntity entity = new FeatureEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setGloballyEnabled(true);
        entity.setUsers(Collections.emptySet());

        FeatureBo bo = FeatureBoToFeatureEntityFactory.toBo(entity);

        assertNotNull(bo);
        assertEquals("name", bo.getName());
        assertTrue(bo.isEnabled());
    }

    @Test
    public void testToBoWithDisabled() {
        FeatureEntity entity = new FeatureEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setGloballyEnabled(false);
        entity.setUsers(Collections.emptySet());

        FeatureBo bo = FeatureBoToFeatureEntityFactory.toBo(entity);

        assertNotNull(bo);
        assertEquals("name", bo.getName());
        assertFalse(bo.isEnabled());
    }

    @Test
    public void testToBoWithDisabledParam() {
        FeatureEntity entity = new FeatureEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setGloballyEnabled(false);
        entity.setUsers(Collections.emptySet());

        FeatureBo bo = FeatureBoToFeatureEntityFactory.toBo(entity, true);

        assertNotNull(bo);
        assertEquals("name", bo.getName());
        assertTrue(bo.isEnabled());
    }

    @Test
    public void testToBoWithEnabledParam() {
        FeatureEntity entity = new FeatureEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setGloballyEnabled(true);
        entity.setUsers(Collections.emptySet());

        FeatureBo bo = FeatureBoToFeatureEntityFactory.toBo(entity, false);

        assertNotNull(bo);
        assertEquals("name", bo.getName());
        assertFalse(bo.isEnabled());
    }

    @Test
    public void testToEntity() {
        FeatureBo bo = new FeatureBo();
        bo.setName("name");
        bo.setEnabled(true);

        FeatureEntity entity = FeatureBoToFeatureEntityFactory.toEntity(bo);

        assertNotNull(entity);
        assertEquals("name", entity.getName());
        assertTrue(entity.isGloballyEnabled());
    }
}
