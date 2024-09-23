package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_roles", schema = "dvc_hanghai_liferay_clone")
public class UsersRole {
    @EmbeddedId
    private UsersRoleId id;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    //TODO [Reverse Engineering] generate columns from DB
}