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
@Table(name = "dm_port_region", schema = "dvc_hanghai_nghiepvu_clone")
public class DmPortRegion {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PortRegionCode", length = 12)
    private String portRegionCode;

    @Column(name = "PortRegionName", length = 500)
    private String portRegionName;

    @ColumnDefault("''")
    @Column(name = "PortRegionNameVN", length = 100)
    private String portRegionNameVN;

    @Column(name = "PortRegionRef", length = 16)
    private String portRegionRef;

    @Column(name = "PortCode", length = 500)
    private String portCode;

    @Column(name = "PortCodeRef", length = 16)
    private String portCodeRef;

    @ColumnDefault("''")
    @Column(name = "PortRegionShortName", length = 100)
    private String portRegionShortName;

    @ColumnDefault("''")
    @Column(name = "Note", length = 50)
    private String note;

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