package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

public class AvailableFeaturesDto {

    private Set<FeatureDto> enabledFeatures;

    public Set<FeatureDto> getEnabledFeatures() {
        return enabledFeatures;
    }

    public void setEnabledFeatures(Set<FeatureDto> enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enabledFeatures", enabledFeatures)
                .toString();
    }
}
