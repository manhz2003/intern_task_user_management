package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlsync", schema = "dvc_hanghai_liferay_clone")
public class Dlsync {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "syncId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "fileId")
    private Long fileId;

    @Column(name = "fileUuid", length = 75)
    private String fileUuid;

    @Column(name = "repositoryId")
    private Long repositoryId;

    @Column(name = "parentFolderId")
    private Long parentFolderId;

    @Column(name = "name")
    private String name;

    @Column(name = "event", length = 75)
    private String event;

    @Column(name = "type_", length = 75)
    private String type;

    @Column(name = "version", length = 75)
    private String version;

}