package com.task.features.service.model;

import com.task.features.rest.dto.UserFeaturesDto;

import java.util.stream.Collectors;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class UserBoToUserFeatureDto {

    public static UserFeaturesDto toDto(UserBo bo) {
        UserFeaturesDto dto = new UserFeaturesDto();
        dto.setId(bo.getId());
        dto.setFeatures(bo.getFeatures().stream().map(FeatureBoToFeatureDtoFactory::toDto).collect(Collectors.toSet()));

        return dto;
    }
}
