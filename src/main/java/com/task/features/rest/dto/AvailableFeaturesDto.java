package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableFeaturesDto that = (AvailableFeaturesDto) o;
        return Objects.equals(enabledFeatures, that.enabledFeatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabledFeatures);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enabledFeatures", enabledFeatures)
                .toString();
    }
}
