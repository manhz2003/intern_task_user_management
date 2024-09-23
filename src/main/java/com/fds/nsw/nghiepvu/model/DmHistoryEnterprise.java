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
@Table(name = "dm_history_enterprise", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryEnterprise {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "EnterpriseCode", length = 50)
    private String enterpriseCode;

    @Column(name = "EnterpriseTaxCode", length = 50)
    private String enterpriseTaxCode;

    @Column(name = "EnterpriseName", length = 575)
    private String enterpriseName;

    @Column(name = "EnterpriseShortName", length = 50)
    private String enterpriseShortName;

    @Column(name = "EnterpriseForeignName", length = 575)
    private String enterpriseForeignName;

    @Column(name = "EnterprisePerson", length = 575)
    private String enterprisePerson;

    @Column(name = "EnterpriseHOAddress", length = 575)
    private String enterpriseHOAddress;

    @Column(name = "StateCode", length = 50)
    private String stateCode;

    @Column(name = "CityCode", length = 50)
    private String cityCode;

    @Column(name = "DistrictCode", length = 50)
    private String districtCode;

    @Column(name = "WardCode", length = 50)
    private String wardCode;

    @Column(name = "TelephoneNo", length = 50)
    private String telephoneNo;

    @Column(name = "RegistrationCode", length = 50)
    private String registrationCode;

    @Column(name = "RegistrationAddress", length = 575)
    private String registrationAddress;

    @Column(name = "RegistrationDate")
    private Date registrationDate;

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

    @Column(name = "ModifiedUser", length = 50)
    private String modifiedUser;

}