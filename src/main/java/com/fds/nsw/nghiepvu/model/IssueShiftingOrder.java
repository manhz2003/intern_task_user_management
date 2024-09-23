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
@Table(name = "issue_shifting_order", schema = "dvc_hanghai_nghiepvu_clone")
public class IssueShiftingOrder {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "NumberShiftingOrder", length = 30)
    private String numberShiftingOrder;

    @Column(name = "DocumentName")
    private long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "PortofAuthority", length = 100)
    private String portofAuthority;

    @Column(name = "NameOfShip", length = 100)
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "AnchoringPortCode", length = 6)
    private String anchoringPortCode;

    @Column(name = "AnchoringPortWharfCode", length = 6)
    private String anchoringPortWharfCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "ShiftingPortWharfCode", length = 5)
    private String shiftingPortWharfCode;

    @Column(name = "ShiftingDate")
    private Date shiftingDate;

    @Column(name = "ReasonToShift", length = 250)
    private String reasonToShift;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "DirectorOfMaritime", length = 100)
    private String directorOfMaritime;

    @Column(name = "CertificateNo", length = 100)
    private String certificateNo;

    @ColumnDefault("1")
    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "VersionNo", length = 6)
    private String versionNo;

    @Column(name = "IsApproval")
    private Integer isApproval;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ApprovalDate")
    private Date approvalDate;

    @Column(name = "ApprovalName", length = 100)
    private String approvalName;

    @Column(name = "Remarks", length = 600)
    private String remarks;

    @Column(name = "IsCancel")
    private Integer isCancel;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "CancelDate")
    private Date cancelDate;

    @Column(name = "CancelName", length = 100)
    private String cancelName;

    @ColumnDefault("'System'")
    @Column(name = "CancelNote", length = 600)
    private String cancelNote;

    @ColumnDefault("'GIÁM ĐỐC'")
    @Column(name = "Representative", length = 600)
    private String representative;

        @ColumnDefault("0")
    @Column(name = "DigitalAttachedFile") 
    private Integer digitalAttachedFile=0;

    @Column(name = "SignName", length = 600)
    private String signName;

    @Column(name = "SignTitle", length = 600)
    private String signTitle;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "SignPlace", length = 600)
    private String signPlace;

    @Column(name = "AttachedFile")
    private Long attachedFile;

    @Column(name = "StampStatus")
    private Integer stampStatus;

    @Column(name = "ShownDraftxF")
    private Double shownDraftxF;

    @Column(name = "UnitShownDraftxF")
    private String unitShownDraftxF;

    @Column(name = "ShownDraftxA")
    private Double shownDraftxA;

    @Column(name = "UnitShownDraftxA")
    private String unitShownDraftxA;

    @Column(name = "LOA")
    private Double loa;

    @Column(name = "LOAUNIT")
    private String loaunit;

    @Column(name = "DWT")
    private Double dwt;

    @Column(name = "DWTUNIT")
    private String dwtunit;

    @Column(name = "TugBoat")
    private String tugBoat;

    @Column(name = "From_")
    private String from;

    @Column(name = "To_")
    private String to;

    @Column(name = "TaxCodeOfShipownersAgents")
    private String taxCodeOfShipownersAgents;

    @Column(name = "NameOfShipownersAgents")
    private String nameOfShipownersAgents;

    @Column(name = "ShipAgencyAddress")
    private String shipAgencyAddress;

    @Column(name = "ShipAgencyPhone")
    private String shipAgencyPhone;

    @Column(name = "ShipAgencyFax")
    private String shipAgencyFax;

    @Column(name = "ShipAgencyEmail")
    private String shipAgencyEmail;

}