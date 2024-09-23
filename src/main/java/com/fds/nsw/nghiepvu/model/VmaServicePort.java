package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_service_port", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaServicePort {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "ServicePortCompanyCode", length = 75)
    private String servicePortCompanyCode;

    @Column(name = "ServicePortCompanyName")
    private String servicePortCompanyName;

    @Column(name = "CompanyAddress")
    private String companyAddress;

    @Column(name = "ContactEmail", length = 75)
    private String contactEmail;

    @Column(name = "TelNo", length = 75)
    private String telNo;

    @Column(name = "FaxNo", length = 75)
    private String faxNo;

    @Lob
    @Column(name = "Remarks")
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