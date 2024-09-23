package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfileversion", schema = "dvc_hanghai_liferay_clone")
public class Dlfileversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileVersionId", nullable = false)
    private Long id;

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

    @Column(name = "folderId")
    private Long folderId;

    @Column(name = "fileEntryId")
    private Long fileEntryId;

    @Column(name = "extension", length = 75)
    private String extension;

    @Column(name = "mimeType", length = 75)
    private String mimeType;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "changeLog", length = 75)
    private String changeLog;

    @Lob
    @Column(name = "extraSettings")
    private String extraSettings;

    @Column(name = "fileEntryTypeId")
    private Long fileEntryTypeId;

    @Column(name = "version", length = 75)
    private String version;

    @Column(name = "size_")
    private Long size;

    @Column(name = "status")
    private Integer status;

    @Column(name = "statusByUserId")
    private Long statusByUserId;

    @Column(name = "statusByUserName", length = 75)
    private String statusByUserName;

    @Column(name = "statusDate")
    private Date statusDate;

}