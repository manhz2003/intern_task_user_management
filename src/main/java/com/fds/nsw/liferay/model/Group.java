package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "group_", schema = "dvc_hanghai_liferay_clone")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "creatorUserId")
    private Long creatorUserId;

    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "classPK")
    private Long classPK;

    @Column(name = "parentGroupId")
    private Long parentGroupId;

    @Column(name = "liveGroupId")
    private Long liveGroupId;

    @Column(name = "name", length = 150)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "type_")
    private Integer type;

    @Lob
    @Column(name = "typeSettings")
    private String typeSettings;

    @Column(name = "friendlyURL", length = 100)
    private String friendlyURL;

    @Column(name = "site")
    private Byte site;

    @Column(name = "active_")
    private Byte active;

}