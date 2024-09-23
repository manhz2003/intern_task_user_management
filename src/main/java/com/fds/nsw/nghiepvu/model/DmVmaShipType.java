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
@Table(name = "dm_vma_ship_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmVmaShipType {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ShipTypeCode", length = 75)
    private String shipTypeCode;

    @Column(name = "ShipTypeName", length = 75)
    private String shipTypeName;

    @Column(name = "ApplyShip", length = 75)
    private String applyShip;

    @Column(name = "ApplyBoat", length = 75)
    private String applyBoat;

    @Column(name = "ShipTypeRef", length = 75)
    private String shipTypeRef;

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