package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureResponseDto dto = (FeatureResponseDto) o;
        return Objects.equals(name, dto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
