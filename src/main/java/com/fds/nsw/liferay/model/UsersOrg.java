package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_orgs", schema = "dvc_hanghai_liferay_clone")
//@IdClass(UsersOrgId.class)
public class UsersOrg {
    @EmbeddedId
    private UsersOrgId id;
//
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "organizationId", insertable = false, updatable = false)
    private Organization organization;


//    @Id
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "organizationId")
//    private Organization organization;

    //TODO [Reverse Engineering] generate columns from DB
}