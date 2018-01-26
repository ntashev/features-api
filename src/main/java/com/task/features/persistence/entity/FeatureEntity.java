package com.task.features.persistence.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representation of features.
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_features",
            joinColumns =  @JoinColumn(name = "feature_id") ,
            inverseJoinColumns =  @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();

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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureEntity that = (FeatureEntity) o;
        return isGloballyEnabled == that.isGloballyEnabled &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isGloballyEnabled);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("isGloballyEnabled", isGloballyEnabled)
                .append("users", users)
                .toString();
    }
}
