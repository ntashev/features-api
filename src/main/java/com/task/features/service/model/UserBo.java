package com.task.features.service.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class UserBo {

    private Integer id;
    private String firstName;
    private String lastName;
    private Set<FeatureBo> features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<FeatureBo> getFeatures() {
        return features;
    }

    public void setFeatures(Set<FeatureBo> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("features", features)
                .toString();
    }

}
