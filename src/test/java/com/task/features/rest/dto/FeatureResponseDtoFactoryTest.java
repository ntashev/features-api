package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class FeatureResponseDtoFactoryTest {

    @Test
    public void testToDto() {
        FeatureBo bo = new FeatureBo();
        bo.setName("name");
        bo.setEnabled(true);

        FeatureResponseDto dto = FeatureResponseDtoFactory.toDto(bo);

        assertNotNull(dto);
        assertEquals("name", dto.getName());
    }
}
