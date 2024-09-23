package com.fds.nsw.liferay.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usergrouprole", schema = "dvc_hanghai_liferay_clone")
public class Usergrouprole {
    @EmbeddedId
    private UsergrouproleId id;

    //TODO [Reverse Engineering] generate columns from DB
}