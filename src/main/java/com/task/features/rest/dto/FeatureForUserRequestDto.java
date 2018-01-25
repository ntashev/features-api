package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Data transfer object representation of feature for user request body.
 */
public class FeatureForUserRequestDto {

    @Min(1)
    @NotNull
    private Integer featureId;

    @NotNull
    private Boolean enabled;

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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
