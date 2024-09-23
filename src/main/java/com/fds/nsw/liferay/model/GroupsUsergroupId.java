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
public class GroupsUsergroupId implements Serializable {
    private static final long serialVersionUID = -3458903359476491271L;
    @Column(name = "groupId", nullable = false)
    private Long groupId;

    @Column(name = "userGroupId", nullable = false)
    private Long userGroupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupsUsergroupId entity = (GroupsUsergroupId) o;
        return Objects.equals(this.userGroupId, entity.userGroupId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, groupId);
    }

}