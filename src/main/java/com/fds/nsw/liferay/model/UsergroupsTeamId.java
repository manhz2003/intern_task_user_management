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
public class UsergroupsTeamId implements Serializable {
    private static final long serialVersionUID = -7526295665475591777L;
    @Column(name = "userGroupId", nullable = false)
    private Long userGroupId;

    @Column(name = "teamId", nullable = false)
    private Long teamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsergroupsTeamId entity = (UsergroupsTeamId) o;
        return Objects.equals(this.userGroupId, entity.userGroupId) &&
                Objects.equals(this.teamId, entity.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, teamId);
    }

}