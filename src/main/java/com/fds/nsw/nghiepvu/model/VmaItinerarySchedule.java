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
@Table(name = "vma_itinerary_schedule", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaItinerarySchedule {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

        @ColumnDefault("0")
    @Column(name = "NoticeShipType") 
    private Integer noticeShipType=0;

    @Column(name = "CertificateNo", length = 100)
    private String certificateNo;

    @Column(name = "ShipBoat", length = 10)
    private String shipBoat;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "ShipPreviousName")
    private String shipPreviousName;

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

    @Column(name = "ShipAgencyContactEmail", length = 575)
    private String shipAgencyContactEmail;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyPhone", length = 575)
    private String shipAgencyPhone;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyFax", length = 575)
    private String shipAgencyFax;

    @Column(name = "SecurityLevelCode", length = 10)
    private String securityLevelCode;

    @ColumnDefault("''")
    @Column(name = "RouteLevelCode", length = 10)
    private String routeLevelCode;

    @Column(name = "ArrivalPortCode", length = 5)
    private String arrivalPortCode;

    @Lob
    @Column(name = "InitFrom")
    private String initFrom;

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

    @Column(name = "AsPerManifest")
    private Integer asPerManifest;

    @Column(name = "ContainerNumber", precision = 18)
    private BigDecimal containerNumber;

    @Column(name = "NumberTEU", precision = 18, scale = 2)
    private BigDecimal numberTEU;

    @Column(name = "NumberTNE", precision = 18, scale = 2)
    private BigDecimal numberTNE;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @Column(name = "TimeOfArrival")
    private Date timeOfArrival;

    @Column(name = "TimeOfPORTArrival")
    private Date timeOfPORTArrival;

    @Column(name = "TimeOfPilotOnBoard")
    private Date timeOfPilotOnBoard;

    @Column(name = "TimeOfApproval")
    private Date timeOfApproval;

    @Column(name = "TugBoat")
    private String tugBoat;

    @Column(name = "DO_")
    private String doField;

    @Column(name = "FO_")
    private String fo;

    @Column(name = "FW_")
    private String fw;

    @Column(name = "OilWater")
    private String oilWater;

    @Lob
    @Column(name = "QuantityOfCargo")
    private String quantityOfCargo;

    @Lob
    @Column(name = "RemainingCargo")
    private String remainingCargo;

    @Column(name = "ShipRequirementsInTermsOfWaste")
    private String shipRequirementsInTermsOfWaste;

    @Column(name = "PreviousPortsOfCall")
    private String previousPortsOfCall;

    @Column(name = "SubsequentPortsOfCall")
    private String subsequentPortsOfCall;

    @Column(name = "DischargedPorts")
    private String dischargedPorts;

    @Column(name = "PortGoingToStateName")
    private String portGoingToStateName;

    @Column(name = "PortGoingToCode")
    private String portGoingToCode;

    @Column(name = "LastPortOfCallCode")
    private String lastPortOfCallCode;

    @Column(name = "LastProvinceCode")
    private String lastProvinceCode;

    @Column(name = "LastMaritimePortCode", length = 5)
    private String lastMaritimePortCode;

    @Column(name = "LastPortRegionCode")
    private String lastPortRegionCode;

    @Column(name = "LastPortHarbourCode")
    private String lastPortHarbourCode;

    @Column(name = "LastPortWharfCode")
    private String lastPortWharfCode;

    @Column(name = "NextProvinceCode")
    private String nextProvinceCode;

    @Column(name = "NextMaritimePortCode")
    private String nextMaritimePortCode;

    @Column(name = "NextPortRegionCode")
    private String nextPortRegionCode;

    @Column(name = "NextPortHarbourCode")
    private String nextPortHarbourCode;

    @Column(name = "NextPortWharfCode")
    private String nextPortWharfCode;

    @Column(name = "ChanelCodeList")
    private String chanelCodeList;

    @Column(name = "ChanelName")
    private String chanelName;

    @Column(name = "AnchorageFreeDuration")
    private Double anchorageFreeDuration;

    @Column(name = "CargoType", length = 12)
    private String cargoType;

    @Column(name = "DepartmentCode", length = 30)
    private String departmentCode;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "CheckNoticeApproval")
    private Integer checkNoticeApproval;

    @Column(name = "CheckBerthPlan")
    private Integer checkBerthPlan;

    @Column(name = "CheckPilotPlan")
    private Integer checkPilotPlan;

    @Column(name = "CheckTugboatPlan")
    private Integer checkTugboatPlan;

    @Column(name = "MergedShip", length = 50)
    private String mergedShip;

    @Column(name = "Violated")
    private Integer violated;

    @Column(name = "Deconstructed")
    private Integer deconstructed;

    @Lob
    @Column(name = "maritimeRemarks", nullable = false)
    private String maritimeRemarks;

    @Lob
    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "RequestCodeNoticeShipMessage")
    private String requestCodeNoticeShipMessage;

    @Column(name = "RequestCodeGeneralDeclaration")
    private String requestCodeGeneralDeclaration;

    @Column(name = "RequestCodeShipSecurityMessage")
    private String requestCodeShipSecurityMessage;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}