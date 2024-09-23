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
@Table(name = "dm_port", schema = "dvc_hanghai_nghiepvu_clone")
public class DmPort {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PortCode", length = 8)
    private String portCode;

    @Column(name = "LoCode", length = 5)
    private String loCode;

    @Column(name = "PortName", length = 250)
    private String portName;

    @Column(name = "FullName", length = 300)
    private String fullName;

    @Column(name = "FullNameVN", length = 300)
    private String fullNameVN;

    @ColumnDefault("'0'")
    @Column(name = "PortType", length = 6)
    private String portType;

    @Column(name = "PortOrder")
    private Integer portOrder;

    @Column(name = "Address", length = 250)
    private String address;

    @Column(name = "AddressVN", length = 250)
    private String addressVN;

    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "CityCode", length = 12)
    private String cityCode;

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