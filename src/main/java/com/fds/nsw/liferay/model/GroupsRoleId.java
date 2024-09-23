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
public class GroupsRoleId implements Serializable {
    private static final long serialVersionUID = -3206825560757767014L;
    @Column(name = "groupId", nullable = false)
    private Long groupId;

    @Column(name = "roleId", nullable = false)
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupsRoleId entity = (GroupsRoleId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, groupId);
    }

}