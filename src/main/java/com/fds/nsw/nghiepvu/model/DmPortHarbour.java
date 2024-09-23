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
@Table(name = "dm_port_harbour", schema = "dvc_hanghai_nghiepvu_clone")
public class DmPortHarbour {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "PortHarbourName", length = 100)
    private String portHarbourName;

    @ColumnDefault("''")
    @Column(name = "PortHarbourNameVN", length = 100)
    private String portHarbourNameVN;

    @Column(name = "PortHarbourType")
    private Integer portHarbourType;

    @Column(name = "PortRegion", length = 11)
    private String portRegion;

    @Column(name = "PortCode", length = 50)
    private String portCode;

    @Column(name = "PortRegionCode", length = 12)
    private String portRegionCode;

    @Column(name = "Note", length = 50)
    private String note;

    @ColumnDefault("''")
    @Column(name = "PortHarbourShortName", length = 100)
    private String portHarbourShortName;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

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