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
@Table(name = "dm_gt_route_config", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtRouteConfig {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RouteCode", nullable = false, length = 12)
    private String routeCode;

    @Column(name = "OrganizationCode", nullable = false, length = 12)
    private String organizationCode;

    @Column(name = "LocCode", nullable = false, length = 12)
    private String locCode;

    @Column(name = "IPName", length = 250)
    private String ipName;

    @Column(name = "PortName", length = 250)
    private String portName;

    @Column(name = "LinkAddress", length = 250)
    private String linkAddress;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete = 0;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 600)
    private String syncVersion;

}