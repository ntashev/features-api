package com.task.features.service.model;

import com.task.features.rest.dto.FeatureRequestDto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class FeatureBoFactoryTest {

    @Test
    public void testToBo() {
        FeatureRequestDto dto = new FeatureRequestDto();
        dto.setEnabled(Boolean.TRUE);
        dto.setName("name");

        FeatureBo bo = FeatureBoFactory.toBo(dto);

        assertNotNull(bo);
        assertTrue(dto.isEnabled());
        assertEquals("name", bo.getName());
    }
}
