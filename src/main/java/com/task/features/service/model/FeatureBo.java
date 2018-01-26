package com.task.features.service.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Business object representation of features.
 */
public class FeatureBo {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureBo featureBo = (FeatureBo) o;
        return isEnabled == featureBo.isEnabled &&
                Objects.equals(name, featureBo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isEnabled);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("isGloballyEnabled", isEnabled)
                .toString();
    }
}
