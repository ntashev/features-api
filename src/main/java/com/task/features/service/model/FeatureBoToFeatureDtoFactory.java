package com.task.features.service.model;

import com.task.features.rest.dto.FeatureDto;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class FeatureBoToFeatureDtoFactory {

    public static FeatureBo toBo(FeatureDto dto) {
        FeatureBo bo = new FeatureBo();
        bo.setName(dto.getName());
        bo.setEnabled(dto.isEnabled());

        return bo;
    }

    public static FeatureDto toDto(FeatureBo bo) {
        FeatureDto dto = new FeatureDto();
        dto.setName(bo.getName());
        dto.setEnabled(bo.isEnabled());

        return dto;
    }
}
