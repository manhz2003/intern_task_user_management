package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "organization_", schema = "dvc_hanghai_liferay_clone")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizationId", nullable = false)
    private Long organizationId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "parentOrganizationId")
    private Long parentOrganizationId;

    @Lob
    @Column(name = "treePath")
    private String treePath;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "type_", length = 75)
    private String type;

    @Column(name = "recursable")
    private Byte recursable;

    @Column(name = "regionId")
    private Long regionId;

    @Column(name = "countryId")
    private Long countryId;

    @Column(name = "statusId")
    private Integer statusId;

    @Lob
    @Column(name = "comments")
    private String comments;

//    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
//    private Set<UsersOrg> usersOrgs = new HashSet<>();
}