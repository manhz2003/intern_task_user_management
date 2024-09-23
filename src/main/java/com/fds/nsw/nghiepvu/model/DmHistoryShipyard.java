package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_history_shipyard", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryShipyard {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @ColumnDefault("''")
    @Column(name = "ShipyardCode", length = 75)
    private String shipYardCode;

    @Column(name = "TaxCode", length = 75)
    private String taxCode;

    @Column(name = "CompanyName", length = 275)
    private String companyName;

    @Lob
    @Column(name = "CompanyAddress")
    private String companyAddress;

    @Column(name = "CompanyShortName", length = 50)
    private String companyShortName;

    @Column(name = "MarkupMaintainane")
    private Integer markupMaintainane;

    @Column(name = "MarkupConstruction")
    private Integer markupConstruction;

    @Column(name = "MarkupDeconstruction")
    private Integer markupDeconstruction;

    @Lob
    @Column(name = "ProfileMaintainane")
    private String profileMaintainane;

    @Lob
    @Column(name = "ProfileConstruction")
    private String profileConstruction;

    @Lob
    @Column(name = "ProfileDeconstruction")
    private String profileDeconstruction;

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