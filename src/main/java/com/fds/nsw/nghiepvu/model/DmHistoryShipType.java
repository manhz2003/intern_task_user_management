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
@Table(name = "dm_history_ship_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryShipType {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "ShipTypeCode", length = 12)
    private String shipTypeCode;

    @Column(name = "ShipTypeName", length = 100)
    private String shipTypeName;

    @Column(name = "ShipTypeNameVN", length = 100)
    private String shipTypeNameVN;

    @Column(name = "ShipTypeOrder")
    private Integer shipTypeOrder;

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