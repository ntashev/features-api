package com.task.features.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class UserFeaturesDto {

    private Integer id;
    private Set<FeatureDto> features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(Set<FeatureDto> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("features", features)
                .toString();
    }
}
