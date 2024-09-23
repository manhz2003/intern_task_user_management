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
@Table(name = "dm_vma_pilot_category", schema = "dvc_hanghai_nghiepvu_clone")
public class DmVmaPilotCategory {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PilotCategoryCode", length = 75)
    private String pilotCategoryCode;

    @Column(name = "PilotCategoryName", length = 75)
    private String pilotCategoryName;

    @Column(name = "MaxLength", length = 75)
    private String maxLength;

    @Column(name = "GrossTonage", length = 75)
    private String grossTonage;

    @Column(name = "SafeTime", length = 75)
    private String safeTime;

    @Column(name = "Remarks", length = 75)
    private String remarks;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 75)
    private String syncVersion;

}