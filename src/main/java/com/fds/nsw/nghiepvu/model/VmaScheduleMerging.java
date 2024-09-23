package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_merging", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleMerging {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

        @ColumnDefault("0")
    @Column(name = "NoticeShipType") 
    private Integer noticeShipType=0;

    @Column(name = "ShipBoat", length = 10)
    private String shipBoat;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @Column(name = "ShipOwnersName")
    private String shipOwnersName;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @Column(name = "ShipOperatorName")
    private String shipOperatorName;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @Column(name = "ShipAgencyAddress", length = 575)
    private String shipAgencyAddress;

    @Column(name = "ShipAgencyContactEmail", length = 50)
    private String shipAgencyContactEmail;

    @Column(name = "ShipAgencyPhone", length = 50)
    private String shipAgencyPhone;

    @Column(name = "ShipAgencyFax", length = 50)
    private String shipAgencyFax;

    @Column(name = "SecurityLevelCode", length = 10)
    private String securityLevelCode;

    @Column(name = "ArrivalPortCode", length = 5)
    private String arrivalPortCode;

    @Column(name = "PortRegionCode", length = 3)
    private String portRegionCode;

    @Column(name = "PortHarbourCode", length = 5)
    private String portHarbourCode;

    @Column(name = "PortWharfCode", length = 5)
    private String portWharfCode;

    @ColumnDefault("0.00")
    @Column(name = "GT", precision = 18, scale = 2)
    private BigDecimal gt;

    @ColumnDefault("0.00")
    @Column(name = "NT", precision = 18, scale = 2)
    private BigDecimal nt;

    @ColumnDefault("0.00")
    @Column(name = "DWT", precision = 18, scale = 2)
    private BigDecimal dwt;

    @ColumnDefault("0.00")
    @Column(name = "LOA")
    private Double loa;

    @ColumnDefault("0.00")
    @Column(name = "Breadth")
    private Double breadth;

    @ColumnDefault("0.00")
    @Column(name = "ClearanceHeight")
    private Double clearanceHeight;

    @ColumnDefault("0.00")
    @Column(name = "Power")
    private Double power;

    @ColumnDefault("0.00")
    @Column(name = "MaxDraft")
    private Double maxDraft;

    @ColumnDefault("0.00")
    @Column(name = "ShownDraftxF")
    private Double shownDraftxF;

    @ColumnDefault("0.00")
    @Column(name = "ShownDraftxA")
    private Double shownDraftxA;

    @Column(name = "UnitLOA", length = 10)
    private String unitLOA;

    @Column(name = "UnitBreadth", length = 10)
    private String unitBreadth;

    @Column(name = "UnitClearanceHeight", length = 10)
    private String unitClearanceHeight;

    @Column(name = "UnitShownDraftxF", length = 10)
    private String unitShownDraftxF;

    @Column(name = "UnitShownDraftxA", length = 10)
    private String unitShownDraftxA;

    @Column(name = "UnitGRT", length = 10)
    private String unitGRT;

    @Column(name = "UnitNT", length = 10)
    private String unitNT;

    @Column(name = "UnitDWT", length = 10)
    private String unitDWT;

    @Column(name = "UnitPower", length = 10)
    private String unitPower;

    @Column(name = "UnitMaxDraft", length = 10)
    private String unitMaxDraft;

    @Column(name = "PurposeCode", length = 20)
    private String purposeCode;

    @Column(name = "PurposeSpecified")
    private String purposeSpecified;

    @Column(name = "CrewNumber", precision = 18)
    private BigDecimal crewNumber;

    @Column(name = "PassengerNumber", precision = 18)
    private BigDecimal passengerNumber;

    @ColumnDefault("''")
    @Column(name = "ShipLashName")
    private String shipLashName;

    @Column(name = "MergeStatus", length = 20)
    private String mergeStatus;

    @Column(name = "MergeDateFrom")
    private Date mergeDateFrom;

    @Column(name = "MergeDateTo")
    private Date mergeDateTo;

    @ColumnDefault("''")
    @Column(name = "ShipTypeMT", length = 20)
    private String shipTypeMT;

    @ColumnDefault("''")
    @Column(name = "ShipTypeCode", length = 20)
    private String shipTypeCode;

    @ColumnDefault("''")
    @Column(name = "ShipLashIMONumber", length = 50)
    private String shipLashIMONumber;

    @ColumnDefault("''")
    @Column(name = "ShipLashCallSign", length = 20)
    private String shipLashCallSign;

    @ColumnDefault("''")
    @Column(name = "ShipLashFlagStateOfShip", length = 20)
    private String shipLashFlagStateOfShip;

    @ColumnDefault("''")
    @Column(name = "ShipLashVRCode", length = 20)
    private String shipLashVRCode;

    @Column(name = "ShipLashRegistryNumber", length = 30)
    private String shipLashRegistryNumber;

        @ColumnDefault("0")
    @Column(name = "MakePayment") 
    private Integer makePayment=0;

    @ColumnDefault("''")
    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}