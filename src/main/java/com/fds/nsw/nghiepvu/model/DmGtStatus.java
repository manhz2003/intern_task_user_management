package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_gt_status", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtStatus {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "StatusCode", nullable = false)
    private Integer statusCode;

    @Column(name = "StatusName", nullable = false, length = 250)
    private String statusName;

    @Column(name = "Types")
    private Integer types;

    @Column(name = "Description", length = 250)
    private String description;

    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete;

    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

}