package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class FeatureResponseDtoFactory {

    public static FeatureResponseDto toDto(FeatureBo bo) {
        FeatureResponseDto dto = new FeatureResponseDto();
        dto.setName(bo.getName());

        return dto;
    }
}
