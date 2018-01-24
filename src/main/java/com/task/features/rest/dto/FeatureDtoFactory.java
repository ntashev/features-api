package com.task.features.rest.dto;

import com.task.features.rest.dto.FeatureDto;
import com.task.features.service.model.FeatureBo;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class FeatureDtoFactory {

    public static FeatureDto toDto(FeatureBo bo) {
        FeatureDto dto = new FeatureDto();
        dto.setName(bo.getName());
        dto.setEnabled(bo.isEnabled());

        return dto;
    }
}
