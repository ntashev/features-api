package com.task.features.service.model;

import com.task.features.rest.dto.FeatureRequestDto;

/**
 * Factory to create {@link FeatureBo}.
 */
public class FeatureBoFactory {
    /**
     * Creates features bo from dto.
     *
     * @param dto request dto
     * @return features bo
     */
    public static FeatureBo toBo(FeatureRequestDto dto) {
        FeatureBo bo = new FeatureBo();
        bo.setName(dto.getName());
        bo.setEnabled(dto.isEnabled());

        return bo;
    }
}
