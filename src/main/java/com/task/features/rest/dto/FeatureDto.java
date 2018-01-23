package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author nikolay.tashev on 22/01/2018.
 */
public class FeatureDto {

    private String name;

    private boolean isEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("isEnabled", isEnabled)
                .toString();
    }
}
