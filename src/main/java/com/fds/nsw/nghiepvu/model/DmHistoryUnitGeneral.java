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
@Table(name = "dm_history_unit_general", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryUnitGeneral {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "UnitCode", nullable = false, length = 8)
    private String unitCode;

    @Column(name = "UnitName", nullable = false, length = 250)
    private String unitName;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}