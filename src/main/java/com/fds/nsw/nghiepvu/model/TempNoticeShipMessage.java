package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_notice_ship_message", schema = "dvc_hanghai_nghiepvu_clone")
public class TempNoticeShipMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id = 0L;

    @Column(name = "RequestCode")
    private String requestCode;

    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "NoticeShipType", length = 5)
    private String noticeShipType;

    @Column(name = "NoticeShipCode", length = 5)
    private String noticeShipCode;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "ConfirmTime")
    private Integer confirmTime;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "ShipTypeCode", length = 12)
    private String shipTypeCode;

    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "ShipCaptain")
    private String shipCaptain;

    @Column(name = "IMO", length = 50)
    private String imo;

    //@Column(name = "GRT", precision = 10, scale = 2)
    @Column(name = "GRT")
    private Double grt;

    //@Column(name = "DWT", precision = 10, scale = 2)
    @Column(name = "DWT")
    private Double dwt;

    @Column(name = "UnitGRT", length = 12)
    private String unitGRT;

    @Column(name = "UnitDWT", length = 12)
    private String unitDWT;

    @Column(name = "CallSign")
    private String callSign;

    @Column(name = "ArrivalDate")
    private Date arrivalDate;

    @Column(name = "ArrivalPortCode", length = 12)
    private String arrivalPortCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "PortRegionCode", length = 12)
    private String portRegionCode;

    @Column(name = "PortWharfCode", length = 12)
    private String portWharfCode;

    @Column(name = "PortGoingToStateName", length = 200)
    private String portGoingToStateName;

    @Column(name = "PortGoingToCode", length = 5)
    private String portGoingToCode;

    @Column(name = "NameOfShipOwners")
    private String nameOfShipOwners;

    @Column(name = "AddressOfShipOwners")
    private String addressOfShipOwners;

    @Column(name = "ShipOwnerStateCode", length = 55)
    private String shipOwnerStateCode;

    @Column(name = "ShipOwnerPhone", length = 100)
    private String shipOwnerPhone;

    @Column(name = "ShipOwnerFax", length = 100)
    private String shipOwnerFax;

    @Column(name = "ShipOwnerEmail", length = 50)
    private String shipOwnerEmail;

    //@Column(name = "LOA", precision = 10, scale = 2)
    @Column(name = "LOA")
    private Double loa;

    //@Column(name = "Breadth", precision = 10, scale = 2)
    @Column(name = "Breadth")
    private Double breadth;

    //@Column(name = "ClearanceHeight", precision = 18, scale = 2)
    @Column(name = "ClearanceHeight")
    private Double clearanceHeight;

    //@Column(name = "ShownDraftxF", precision = 18, scale = 2)
    @Column(name = "ShownDraftxF")
    private Double shownDraftxF;

    //@Column(name = "ShownDraftxA", precision = 18, scale = 2)
    @Column(name = "ShownDraftxA")
    private Double shownDraftxA;

    @Column(name = "UnitLOA", length = 12)
    private String unitLOA;

    @Column(name = "UnitBreadth", length = 12)
    private String unitBreadth;

    @Column(name = "UnitClearanceHeight", length = 12)
    private String unitClearanceHeight;

    @Column(name = "UnitShownDraftxF", length = 12)
    private String unitShownDraftxF;

    @Column(name = "UnitShownDraftxA", length = 12)
    private String unitShownDraftxA;

    @Column(name = "CertificateOfRegistryNumber", length = 20)
    private String certificateOfRegistryNumber;

    @Column(name = "CertificateOfRegistryDate")
    private Date certificateOfRegistryDate;

    @Column(name = "CertificateOfRegistryPortName", length = 200)
    private String certificateOfRegistryPortName;

    @Column(name = "ShipAgencyCode")
    private String shipAgencyCode;

    @Column(name = "ShipAgencyAddress")
    private String shipAgencyAddress;

    @Column(name = "ShipAgencyPhone")
    private String shipAgencyPhone;

    @Column(name = "ShipAgencyFax")
    private String shipAgencyFax;

    @Column(name = "ShipAgencyEmail")
    private String shipAgencyEmail;

    @Column(name = "PurposeCode", length = 12)
    private String purposeCode;

    @Column(name = "PurposeSpecified", length = 200)
    private String purposeSpecified;

    @Column(name = "CrewNumber")
    private long crewNumber;

    @Column(name = "UnitCrew", length = 12)
    private String unitCrew;

    @Column(name = "PassengerNumber")
    private long passengerNumber;

    @Column(name = "UnitPassenger", length = 50)
    private String unitPassenger;

    @Lob
    @Column(name = "QuantityOfCargo")
    private String quantityOfCargo;

    @Column(name = "UnitQuantityofCargo", length = 12)
    private String unitQuantityofCargo;

    @Column(name = "TypeOfCargo", length = 12)
    private String typeOfCargo;

    @Column(name = "OtherPersons")
    private Integer otherPersons;

    @Column(name = "Remarks", length = 500)
    private String remarks;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage")
    private byte[] masterSignedImage;

    @Column(name = "NameOfShipAgent", length = 575)
    private String nameOfShipAgent;

    @Column(name = "DocumentReference")
    private Long documentReference;

    @Column(name = "VoyageNumber")
    private String voyageNumber;

    @Column(name = "NetTonnage")
    private Double netTonnage;

    @Column(name = "NetTonnageUnit")
    private String netTonnageUnit;

    @Column(name = "PositionOfShipInPort")
    private String positionOfShipInPort;

    @Column(name = "BriefParticularsOfVoyage")
    private String briefParticularsOfVoyage;

    @Column(name = "RemainingCargo")
    private String remainingCargo;

    @Column(name = "ShipRequirementsInTermsOfWaste")
    private String shipRequirementsInTermsOfWaste;

    @Column(name = "NumberCargoDeclaration")
    private Integer numberCargoDeclaration;

    @Column(name = "NumberShipStoreDeclaration")
    private Integer numberShipStoreDeclaration;

    @Column(name = "NumberCrewList")
    private Integer numberCrewList;

    @Column(name = "NumberPassengerList")
    private Integer numberPassengerList;

    @Column(name = "NumberCrewEffectsDeclaration")
    private Integer numberCrewEffectsDeclaration;

    @Column(name = "NumberHealthMaritimeDeclaration")
    private Integer numberHealthMaritimeDeclaration;

    @Column(name = "PortClearanceNo")
    private String portClearanceNo;

    @Column(name = "TimeOfPORTArrival")
    private Date timeOfPORTArrival;

    @Column(name = "TimeOfPilotOnBoard")
    private Date timeOfPilotOnBoard;

    @Column(name = "TugBoat")
    private String tugBoat;

    @Column(name = "IsArrival", nullable = false)
    private Integer isArrival;

    @Column(name = "PreviousPortsOfCall")
    private String previousPortsOfCall;

    @Column(name = "SubsequentPortsOfCall")
    private String subsequentPortsOfCall;

    @Column(name = "DischargedPorts")
    private String dischargedPorts;

    @Column(name = "DO_")
    private String doField;

    @Column(name = "FO")
    private String fo;

    @Column(name = "FW")
    private String fw;

    @Column(name = "PlaceOfReception", length = 500)
    private String placeOfReception;

}