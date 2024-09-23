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
public class GroupsPermissionId implements Serializable {
    private static final long serialVersionUID = 2065744643470052808L;
    @Column(name = "groupId", nullable = false)
    private Long groupId;

    @Column(name = "permissionId", nullable = false)
    private Long permissionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupsPermissionId entity = (GroupsPermissionId) o;
        return Objects.equals(this.permissionId, entity.permissionId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, groupId);
    }

}