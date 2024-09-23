package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usergroup", schema = "dvc_hanghai_liferay_clone")
public class Usergroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userGroupId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "parentUserGroupId")
    private Long parentUserGroupId;

    @Column(name = "name", length = 75)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "addedByLDAPImport")
    private Byte addedByLDAPImport;

}