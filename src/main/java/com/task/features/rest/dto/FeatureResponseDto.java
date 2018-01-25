package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Data transfer object representation of feature response body.
 */
public class FeatureResponseDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
