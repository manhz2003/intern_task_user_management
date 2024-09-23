package com.fds.nsw.liferay.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_teams", schema = "dvc_hanghai_liferay_clone")
public class UsersTeam {
    @EmbeddedId
    private UsersTeamId id;

    //TODO [Reverse Engineering] generate columns from DB
}