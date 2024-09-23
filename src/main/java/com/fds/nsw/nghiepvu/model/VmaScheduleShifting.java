package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_shifting", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleShifting {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "PortofAuthority", length = 100)
    private String portofAuthority;

    @Column(name = "NameOfShip", length = 100)
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "AnchoringPortHarbourCode", length = 12)
    private String anchoringPortHarbourCode;

    @Column(name = "AnchoringPortWharfCode", length = 6)
    private String anchoringPortWharfCode;

    @Column(name = "ShiftingPortRegionCode", length = 12)
    private String shiftingPortRegionCode;

    @Column(name = "ShiftingPortHarbourCode", length = 12)
    private String shiftingPortHarbourCode;

    @Column(name = "ShiftingPortWharfCode", length = 6)
    private String shiftingPortWharfCode;

    @Column(name = "ShiftingDate")
    private Date shiftingDate;

    @Column(name = "MergedShip", length = 50)
    private String mergedShip;

    @Column(name = "PurposeCode", length = 20)
    private String purposeCode;

    @ColumnDefault("''")
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

    @Column(name = "UnitShownDraftxF", length = 10)
    private String unitShownDraftxF;

    @Column(name = "ShownDraftxA")
    private Double shownDraftxA;

    @Column(name = "UnitShownDraftxA", length = 10)
    private String unitShownDraftxA;

    @ColumnDefault("0.00")
    @Column(name = "GT", precision = 18, scale = 2)
    private BigDecimal gt;

    @ColumnDefault("0.00")
    @Column(name = "NT", precision = 18, scale = 2)
    private BigDecimal nt;

    @Column(name = "LOA")
    private Double loa;

    @Column(name = "LOAUNIT", length = 10)
    private String loaunit;

    @Column(name = "DWT", precision = 18, scale = 2)
    private BigDecimal dwt;

    @Column(name = "DWTUNIT", length = 10)
    private String dwtunit;

    @Column(name = "UnitGRT", length = 10)
    private String unitGRT;

    @Column(name = "UnitNT", length = 10)
    private String unitNT;

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

    @ColumnDefault("''")
    @Column(name = "SecurityLevelCode", length = 50)
    private String securityLevelCode;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyAddress", length = 575)
    private String shipAgencyAddress;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyPhone", length = 575)
    private String shipAgencyPhone;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyFax", length = 575)
    private String shipAgencyFax;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyEmail", length = 575)
    private String shipAgencyEmail;

    @Column(name = "ChanelCodeList")
    private String chanelCodeList;

    @Column(name = "ChanelName")
    private String chanelName;

    @ColumnDefault("''")
    @Column(name = "CargoCode")
    private String cargoCode;

    @Lob
    @Column(name = "QuantityOfCargo")
    private String quantityOfCargo;

    @ColumnDefault("''")
    @Column(name = "DepartmentCode", length = 30)
    private String departmentCode;

    @ColumnDefault("''")
    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}