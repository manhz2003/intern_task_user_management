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
@Table(name = "dm_maritime_hh_2712", schema = "dvc_hanghai_nghiepvu_clone")
public class DmMaritimeHh2712 {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "MaritimeCode", nullable = false, length = 8)
    private String maritimeCode;

    @Column(name = "MaritimeReference", nullable = false, length = 8)
    private String maritimeReference;

    @Column(name = "MaritimeOrder")
    private Integer maritimeOrder;

    @Column(name = "MaritimeName", nullable = false, length = 250)
    private String maritimeName;

    @Column(name = "MaritimeNameVN", nullable = false, length = 250)
    private String maritimeNameVN;

    @Column(name = "Address", length = 250)
    private String address;

    @Column(name = "AddressVN", length = 250)
    private String addressVN;

    @Column(name = "StateCode", length = 20)
    private String stateCode;

    @Column(name = "CityCode", length = 20)
    private String cityCode;

    @Column(name = "ShortName", length = 10)
    private String shortName;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

}