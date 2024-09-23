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
@Table(name = "dm_vrcode", schema = "dvc_hanghai_nghiepvu_clone")
public class DmVRCode {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "VRCode", nullable = false, length = 20)
    private String vrCode;

    @Column(name = "VRCodeName", nullable = false, length = 250)
    private String vrCodeName;

    @ColumnDefault("''")
    @Column(name = "ShipBoat", length = 20)
    private String shipBoat;

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