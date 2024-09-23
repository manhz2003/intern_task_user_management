package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_gt_version", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtVersion {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "VersionName", length = 200)
    private String versionName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "VersionDate", nullable = false)
    private Date versionDate;

    @Column(name = "VersionDeployer", length = 200)
    private String versionDeployer;

    @Column(name = "Contents", length = 500)
    private String contents;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "DBFileName", length = 200)
    private String dBFileName;

    @Column(name = "DBFileSize", length = 50)
    private String dBFileSize;

    @Column(name = "DBDescription", length = 500)
    private String dBDescription;

    @Column(name = "APPFileName", length = 200)
    private String aPPFileName;

    @Column(name = "APPFileSize", length = 50)
    private String aPPFileSize;

    @Column(name = "APPDescription", length = 500)
    private String aPPDescription;

    @Column(name = "SpecFileName", length = 200)
    private String specFileName;

    @Column(name = "SpecFileSize", length = 50)
    private String specFileSize;

    @Column(name = "SpecDescription", length = 500)
    private String specDescription;

    @Column(name = "OrganizationCode", length = 8)
    private String organizationCode;

    @Column(name = "IsDelete")
    private Integer isDelete = 0;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete = 0;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "ModifiedDate", nullable = false)
    private Date modifiedDate;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "RequestedDate", nullable = false)
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}