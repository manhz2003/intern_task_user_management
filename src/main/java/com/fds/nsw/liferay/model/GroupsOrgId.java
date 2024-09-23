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
public class GroupsOrgId implements Serializable {
    private static final long serialVersionUID = -2655293135207326511L;
    @Column(name = "groupId", nullable = false)
    private Long groupId;

    @Column(name = "organizationId", nullable = false)
    private Long organizationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupsOrgId entity = (GroupsOrgId) o;
        return Objects.equals(this.organizationId, entity.organizationId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, groupId);
    }

}