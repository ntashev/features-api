package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;

import java.util.Set;
import java.util.stream.Collectors;

public class AvailableFeaturesDtoFactory {

    public static AvailableFeaturesDto toDto(Set<FeatureBo> features) {
        AvailableFeaturesDto dto = new AvailableFeaturesDto();
        dto.setEnabledFeatures(features.stream().map(FeatureResponseDtoFactory::toDto).collect(Collectors.toSet()));

        return dto;
    }
}
