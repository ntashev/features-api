package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;

/**
 * Factory to create {@link FeatureResponseDto}.
 */
public class FeatureResponseDtoFactory {

    /**
     * Creates {@link FeatureResponseDto} from bo.
     *
     * @param bo business object
     * @return dto
     */
    public static FeatureResponseDto toDto(FeatureBo bo) {
        FeatureResponseDto dto = new FeatureResponseDto();
        dto.setName(bo.getName());

        return dto;
    }
}
