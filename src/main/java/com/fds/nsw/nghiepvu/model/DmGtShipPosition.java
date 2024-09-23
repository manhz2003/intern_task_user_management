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
@Table(name = "dm_gt_ship_position", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtShipPosition {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PositionCode", nullable = false, length = 12)
    private String positionCode;

    @Column(name = "PositionName", nullable = false, length = 250)
    private String positionName;

    @Column(name = "Remarks", nullable = false, length = 250)
    private String remarks;

    @Column(name = "PositionOrder")
    private Integer positionOrder;

    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 600)
    private String syncVersion;

}