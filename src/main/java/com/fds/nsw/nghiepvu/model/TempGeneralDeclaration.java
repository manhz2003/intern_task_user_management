package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_general_declaration", schema = "dvc_hanghai_nghiepvu_clone")
public class TempGeneralDeclaration {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode")
    private String requestCode;

    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "UserCreated", length = 20)
    private String userCreated;

    @Column(name = "IsArrival")
    private Integer isArrival;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "ShipTypeCode", length = 50)
    private String shipTypeCode;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "VoyageNumber", length = 50)
    private String voyageNumber;

    @Column(name = "PortOfArrivalCode", length = 5)
    private String portOfArrivalCode;

    @Column(name = "DateOfArrival")
    private Date dateOfArrival;

    @Column(name = "PortHarbourCode", length = 20)
    private String portHarbourCode;

    @Column(name = "PortRegionCode", length = 200)
    private String portRegionCode;

    @Column(name = "PortWharfCode", length = 20)
    private String portWharfCode;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "NameOfMaster")
    private String nameOfMaster;

    @Column(name = "LastPortOfCallCode", length = 5)
    private String lastPortOfCallCode;

    @Column(name = "CertificateOfRegistryNumber", length = 20)
    private String certificateOfRegistryNumber;

    @Column(name = "CertificateOfRegistryDate")
    private Date certificateOfRegistryDate;

    @Column(name = "CertificateOfRegistryPortName", length = 5)
    private String certificateOfRegistryPortName;

    @Column(name = "TaxCodeOfShipownersAgents")
    private String taxCodeOfShipownersAgents;

    @Column(name = "NameOfShipownersAgents", length = 250)
    private String nameOfShipownersAgents;

    @Column(name = "ShipAgencyAddress")
    private String shipAgencyAddress;

    @Column(name = "ShipAgencyPhone", length = 100)
    private String shipAgencyPhone;

    @Column(name = "ShipAgencyFax", length = 100)
    private String shipAgencyFax;

    @Column(name = "ShipAgencyEmail", length = 50)
    private String shipAgencyEmail;

    @Column(name = "GrossTonnage")
    private double grossTonnage;

    @Column(name = "NetTonnage")
    private double netTonnage;

    @Column(name = "PositionOfShipInPort", length = 250)
    private String positionOfShipInPort;

    @Column(name = "BriefParticularsOfVoyage", length = 500)
    private String briefParticularsOfVoyage;

    @Lob
    @Column(name = "BriefDescriptionOfTheCargo")
    private String briefDescriptionOfTheCargo;

    @Column(name = "NumberOfCrew")
    private Integer numberOfCrew;

    @Column(name = "NumberOfPassengers")
    private Integer numberOfPassengers;

    @Column(name = "ShipRequirementsInTermsOfWaste", length = 500)
    private String shipRequirementsInTermsOfWaste;

    @Column(name = "Remarks", length = 500)
    private String remarks;

    @Column(name = "NumberCargoDeclaration", length = 12)
    private String numberCargoDeclaration;

    @Column(name = "NumberShipStoreDeclaration", length = 12)
    private String numberShipStoreDeclaration;

    @Column(name = "NumberCrewList", length = 12)
    private String numberCrewList;

    @Column(name = "NumberPassengerList", length = 12)
    private String numberPassengerList;

    @Column(name = "NumberCrewEffects", length = 12)
    private String numberCrewEffects;

    @Column(name = "NumberHealthMaritime", length = 12)
    private String numberHealthMaritime;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage", length = 1)
    private String masterSignedImage;

    @Column(name = "CargoOnBoard", length = 100)
    private String cargoOnBoard;

    @Column(name = "Cargo", length = 50)
    private String cargo;

    @Column(name = "VolumeCargo", precision = 18, scale = 2)
    private BigDecimal volumeCargo;

    @Column(name = "CargoUnit", length = 50)
    private String cargoUnit;

    @Column(name = "CargoDescription", length = 50)
    private String cargoDescription;

    @Column(name = "RatedPower", length = 50)
    private String ratedPower;

    @Column(name = "SeatingCapacity", length = 50)
    private String seatingCapacity;

    @Column(name = "LyingCapacity", length = 50)
    private String lyingCapacity;

    @Column(name = "MaritimePortCode", length = 20)
    private String maritimePortCode;

    @Column(name = "LastProvinceCode", length = 5)
    private String lastProvinceCode;

    @Column(name = "NextProvinceCode", length = 5)
    private String nextProvinceCode;

    @Column(name = "LastMaritimePortCode", length = 20)
    private String lastMaritimePortCode;

    @Column(name = "LastPortRegionCode", length = 20)
    private String lastPortRegionCode;

    @Column(name = "LastPortHarbourCode", length = 20)
    private String lastPortHarbourCode;

    @Column(name = "LastPortWharfCode", length = 20)
    private String lastPortWharfCode;

    @Column(name = "NextMaritimePortCode", length = 20)
    private String nextMaritimePortCode;

    @Column(name = "NextPortRegionCode", length = 20)
    private String nextPortRegionCode;

    @Column(name = "NextPortHarbourCode", length = 20)
    private String nextPortHarbourCode;

    @Column(name = "NextPortWharfCode", length = 20)
    private String nextPortWharfCode;

    @Column(name = "DocumentReference")
    private String documentReference;

    @Column(name = "RemainingCargo")
    private String remainingCargo;

    @Column(name = "Link")
    private String link;

    @Column(name = "QCCode")
    private String qcCode;

    @Column(name = "PortClearanceNo")
    private String portClearanceNo;

    @Column(name = "LOA")
    private Double loa;

    @Column(name = "UnitLOA")
    private String unitLOA;

    @Column(name = "Breadth")
    private Double breadth;

    @Column(name = "UnitBreadth")
    private String unitBreadth;

    @Column(name = "ClearanceHeight")
    private Double clearanceHeight;

    @Column(name = "UnitClearanceHeight")
    private String unitClearanceHeight;

    @Column(name = "ShownDraftxF")
    private Double shownDraftxF;

    @Column(name = "UnitShownDraftxF")
    private String unitShownDraftxF;

    @Column(name = "ShownDraftxA")
    private Double shownDraftxA;

    @Column(name = "UnitShownDraftxA")
    private String unitShownDraftxA;

    @Column(name = "DWT")
    private Double dwt;

    @Column(name = "UnitDWT")
    private String unitDWT;

    @Column(name = "TimeOfPORTArrival")
    private Date timeOfPORTArrival;

    @Column(name = "TimeOfPilotOnBoard")
    private Date timeOfPilotOnBoard;

    @Column(name = "TugBoat")
    private String tugBoat;

    @Column(name = "DO_")
    private String doField;

    @Column(name = "FO")
    private String fo;

    @Column(name = "FW")
    private String fw;

    @Column(name = "PreviousPortsOfCall")
    private String previousPortsOfCall;

    @Column(name = "SubsequentPortsOfCall")
    private String subsequentPortsOfCall;

    @Column(name = "DischargedPorts")
    private String dischargedPorts;

    @Column(name = "PlaceOfReception", length = 500)
    private String placeOfReception;

    @Column(name = "NameOfShipOwners")
    private String nameOfShipOwners;

    @Column(name = "AddressOfShipOwners")
    private String addressOfShipOwners;

}