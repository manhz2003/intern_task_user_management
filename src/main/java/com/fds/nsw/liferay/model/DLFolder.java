package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfolder", schema = "dvc_hanghai_liferay_clone")
public class DLFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folderId", nullable = false)
    private Long folderId;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName", length = 75)
    private String userName;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "repositoryId")
    private Long repositoryId;

    @Column(name = "mountPoint")
    private int mountPoint;

    @Column(name = "parentFolderId")
    private Long parentFolderId;

    @Column(name = "name", length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "lastPostDate")
    private Date lastPostDate;

    @Column(name = "defaultFileEntryTypeId")
    private Long defaultFileEntryTypeId;

    @Column(name = "overrideFileEntryTypes")
    private int overrideFileEntryTypes;

}