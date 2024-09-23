package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfileshortcut", schema = "dvc_hanghai_liferay_clone")
public class Dlfileshortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileShortcutId", nullable = false)
    private Long id;

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

    @Column(name = "folderId")
    private Long folderId;

    @Column(name = "toFileEntryId")
    private Long toFileEntryId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "statusByUserId")
    private Long statusByUserId;

    @Column(name = "statusByUserName", length = 75)
    private String statusByUserName;

    @Column(name = "statusDate")
    private Date statusDate;

}