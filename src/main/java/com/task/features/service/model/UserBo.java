package com.task.features.service.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Set;

/**
 * Business onject representation of user.
 */
public class UserBo {

    private String firstName;
    private String lastName;
    private Set<FeatureBo> features;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBo userBo = (UserBo) o;
        return Objects.equals(firstName, userBo.firstName) &&
                Objects.equals(lastName, userBo.lastName) &&
                Objects.equals(features, userBo.features);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, features);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("features", features)
                .toString();
    }

}
