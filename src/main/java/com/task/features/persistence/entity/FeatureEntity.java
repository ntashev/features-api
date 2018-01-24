package com.task.features.persistence.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
@Entity
@Table(name = "features")
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "globally_enabled")
    private boolean isGloballyEnabled;

    @OneToMany(mappedBy = "feature", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<UserFeatureEntity> userFeatures;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGloballyEnabled() {
        return isGloballyEnabled;
    }

    public void setGloballyEnabled(boolean globallyEnabled) {
        isGloballyEnabled = globallyEnabled;
    }

    public Set<UserFeatureEntity> getUserFeatures() {
        return userFeatures;
    }

    public void setUserFeatures(Set<UserFeatureEntity> userFeatures) {
        this.userFeatures = userFeatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureEntity entity = (FeatureEntity) o;
        return isGloballyEnabled == entity.isGloballyEnabled &&
                Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(userFeatures, entity.userFeatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isGloballyEnabled, userFeatures);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("isGloballyEnabled", isGloballyEnabled)
                .append("userFeatures", userFeatures)
                .toString();
    }
}
