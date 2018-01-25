package com.task.features.rest.dto;

import com.task.features.service.model.FeatureBo;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory to create {@link AvailableFeaturesDto}.
 */
public class AvailableFeaturesDtoFactory {

    /**
     * Creates {@link AvailableFeaturesDto} from set of features.
     *
     * @param features features
     * @return dto
     */
    public static AvailableFeaturesDto toDto(Set<FeatureBo> features) {
        AvailableFeaturesDto dto = new AvailableFeaturesDto();
        dto.setEnabledFeatures(features.stream().map(FeatureResponseDtoFactory::toDto).collect(Collectors.toSet()));

        return dto;
    }
}
