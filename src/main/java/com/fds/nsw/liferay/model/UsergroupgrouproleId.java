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
public class UsergroupgrouproleId implements Serializable {
    private static final long serialVersionUID = 6779825139722150843L;
    @Column(name = "userGroupId", nullable = false)
    private Long userGroupId;

    @Column(name = "groupId", nullable = false)
    private Long groupId;

    @Column(name = "roleId", nullable = false)
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsergroupgrouproleId entity = (UsergroupgrouproleId) o;
        return Objects.equals(this.userGroupId, entity.userGroupId) &&
                Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, roleId, groupId);
    }

}