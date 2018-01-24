package com.task.features.service.model;

import com.task.features.rest.dto.FeatureRequestDto;

public class FeatureBoFactory {

    public static FeatureBo toBo(FeatureRequestDto dto) {
        FeatureBo bo = new FeatureBo();
        bo.setName(dto.getName());
        bo.setEnabled(dto.isEnabled());

        return bo;
    }
}
