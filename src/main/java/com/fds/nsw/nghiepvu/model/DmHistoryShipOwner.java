package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_history_ship_owner", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryShipOwner {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "ShipOwnerCode", length = 75)
    private String shipOwnerCode;

    @Column(name = "TaxCode", length = 75)
    private String taxCode;

    @Column(name = "CompanyName", length = 275)
    private String companyName;

    @Lob
    @Column(name = "CompanyAddress")
    private String companyAddress;

    @Column(name = "CompanyShortName", length = 50)
    private String companyShortName;

    @Column(name = "ContactEmail", length = 75)
    private String contactEmail;

    @Lob
    @Column(name = "EmailRecipients")
    private String emailRecipients;

    @Column(name = "TelNo", length = 75)
    private String telNo;

    @Column(name = "FaxNo", length = 75)
    private String faxNo;

    @Column(name = "IsShipOwner")
    private Integer isShipOwner;

    @Column(name = "IsShipOperator")
    private Integer isShipOperator;

        @ColumnDefault("0")
    @Column(name = "IsOther") 
    private Integer isOther=0;

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