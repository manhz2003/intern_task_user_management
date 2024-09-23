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
@Table(name = "dm_security_level", schema = "dvc_hanghai_nghiepvu_clone")
public class DmSecurityLevel {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "SecurityLevelCode", nullable = false, length = 20)
    private String securityLevelCode;

    @Column(name = "SecurityLevel", nullable = false, length = 20)
    private String securityLevel;

    @Column(name = "SecurityLevelName", nullable = false, length = 250)
    private String securityLevelName;

    @ColumnDefault("0")
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete;

    @ColumnDefault("0")
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

}