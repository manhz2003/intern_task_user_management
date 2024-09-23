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
@Table(name = "dm_history_pilot_company", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryPilotCompany {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "PilotCompanyCode", length = 75)
    private String pilotCompanyCode;

    @Column(name = "PilotCompanyName", length = 275)
    private String pilotCompanyName;

    @Column(name = "CompanyAddress", length = 275)
    private String companyAddress;

    @Column(name = "CompanyShortName", length = 50)
    private String companyShortName;

    @Column(name = "ContactEmail", length = 75)
    private String contactEmail;

    @Column(name = "TelNo", length = 75)
    private String telNo;

    @Column(name = "FaxNo", length = 75)
    private String faxNo;

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