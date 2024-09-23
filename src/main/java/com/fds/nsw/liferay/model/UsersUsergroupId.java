package com.fds.nsw.liferay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UsersUsergroupId implements Serializable {
    private static final long serialVersionUID = -4481931979493433714L;
    @Column(name = "userGroupId", nullable = false)
    private Long userGroupId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersUsergroupId entity = (UsersUsergroupId) o;
        return Objects.equals(this.userGroupId, entity.userGroupId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, userId);
    }

}