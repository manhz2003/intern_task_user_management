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
public class UsersOrgId implements Serializable {
    private static final long serialVersionUID = -3991232059045580609L;
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "organizationId", nullable = false)
    private Long organizationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersOrgId entity = (UsersOrgId) o;
        return Objects.equals(this.organizationId, entity.organizationId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, userId);
    }

}