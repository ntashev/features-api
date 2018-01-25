package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * Data transfer object representation of available features.
 */
public class AvailableFeaturesDto {

    private Set<FeatureResponseDto> enabledFeatures;

    public Set<FeatureResponseDto> getEnabledFeatures() {
        return enabledFeatures;
    }

    public void setEnabledFeatures(Set<FeatureResponseDto> enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enabledFeatures", enabledFeatures)
                .toString();
    }
}
