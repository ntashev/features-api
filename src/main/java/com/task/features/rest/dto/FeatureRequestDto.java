package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FeatureRequestDto {

    private String name;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .append("name", name)
                .append("enabled", enabled)
                .toString();
    }
}
