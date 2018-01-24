package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FeatureForUserRequestDto {

    private Integer featureId;
    private boolean enabled;

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("featureId", featureId)
                .append("enabled", enabled)
                .toString();
    }
}
