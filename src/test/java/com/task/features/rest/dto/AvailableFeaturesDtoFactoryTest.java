package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class AvailableFeaturesDtoFactoryTest {

    @Test
    public void testToDtoNonEmptySet() {
        FeatureBo bo = new FeatureBo();
        bo.setEnabled(true);
        bo.setName("name");
        Set<FeatureBo> features = new HashSet<>(Arrays.asList(bo, new FeatureBo()));

        AvailableFeaturesDto result = AvailableFeaturesDtoFactory.toDto(features);

        assertNotNull(result);
        assertNotNull(result.getEnabledFeatures());
        assertEquals(2, result.getEnabledFeatures().size());
    }

    @Test
    public void testToDtoEmptySet() {
        Set<FeatureBo> features = Collections.emptySet();

        AvailableFeaturesDto result = AvailableFeaturesDtoFactory.toDto(features);

        assertNotNull(result);
        assertNotNull(result.getEnabledFeatures());
        assertEquals(0, result.getEnabledFeatures().size());
    }
}
