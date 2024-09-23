package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_itinerary", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaItinerary {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "mtgateway")
    private Integer mtgateway;

    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "VRCode", length = 20)
    private String vrCode;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Column(name = "ShipPosition")
    private Integer shipPosition;

    @Column(name = "ShipTypeCode", length = 20)
    private String shipTypeCode;

    @Column(name = "VmaShipTypeCode", length = 20)
    private String vmaShipTypeCode;

    @Column(name = "ShipCaptain")
    private String shipCaptain;

    @Column(name = "PortofAuthority", length = 20)
    private String portofAuthority;

    @Column(name = "RepresentativeofAuthority")
    private String representativeofAuthority;

    @ColumnDefault("0")
    @Column(name = "MarkedAsArrival")
    private Integer markedAsArrival = 0;

    @ColumnDefault("0")
    @Column(name = "MarkedAsDeparture")
    private Integer markedAsDeparture = 0;

        @ColumnDefault("0")
    @Column(name = "MarkedAsTransmit") 
    private Integer markedAsTransmit=0;

        @ColumnDefault("0")
    @Column(name = "MarkedAsVoyage") 
    private Integer markedAsVoyage=0;

    @Column(name = "DocumentNameIN")
    private Long documentNameIN;

    @Column(name = "DocumentYearIN")
    private Integer documentYearIN;

    @Column(name = "DocumentNameOUT")
    private Long documentNameOUT;

    @Column(name = "DocumentYearOUT")
    private Integer documentYearOUT;

    @Column(name = "DocumentNameTRA")
    private Long documentNameTRA;

    @Column(name = "DocumentYearTRA")
    private Integer documentYearTRA;

    @Column(name = "DocumentNameVOY")
    private Long documentNameVOY;

    @Column(name = "DocumentYearVOY")
    private Integer documentYearVOY;

    @Column(name = "VoyageNumber", length = 50)
    private String voyageNumber;

    @Column(name = "ArrivalPortCode", length = 5)
    private String arrivalPortCode;

    @Column(name = "LastPortCode", length = 5)
    private String lastPortCode;

    @Column(name = "NextPortCode", length = 5)
    private String nextPortCode;

    @Column(name = "DischargedPorts")
    private String dischargedPorts;

    @Lob
    @Column(name = "CargoDescription")
    private String cargoDescription;

    @Column(name = "DomesticTransportCertificate")
    private Integer domesticTransportCertificate;

    @Column(name = "PortClearancePreviousCertificate", length = 30)
    private String portClearancePreviousCertificate;

    @Column(name = "ShipPreviousName")
    private String shipPreviousName;

    @Column(name = "MarkupMaintainane")
    private Integer markupMaintainane;

    @Column(name = "MarkupConstruction")
    private Integer markupConstruction;

    @Column(name = "MarkupDeconstruction")
    private Integer markupDeconstruction;

    @Lob
    @Column(name = "TugboatCondition1")
    private String tugboatCondition1;

    @Lob
    @Column(name = "TugboatCondition2")
    private String tugboatCondition2;

    @Column(name = "TimeOfArrival")
    private Date timeOfArrival;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @ColumnDefault("''")
    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @Column(name = "ShipOwnerName")
    private String shipOwnerName;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @Column(name = "ShipOperatorName")
    private String shipOperatorName;

    @Column(name = "ArrivalShipAgencyCode", length = 30)
    private String arrivalShipAgencyCode;

    @Column(name = "ArrivalShipAgency")
    private String arrivalShipAgency;

    @Column(name = "DepartureShipAgencyCode", length = 30)
    private String departureShipAgencyCode;

    @Column(name = "DepartureShipAgency")
    private String departureShipAgency;

    @ColumnDefault("''")
    @Column(name = "NewShipOwnerCode", length = 30)
    private String newShipOwnerCode;

    @ColumnDefault("''")
    @Column(name = "NewShipOwnerName")
    private String newShipOwnerName;

    @ColumnDefault("''")
    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

    @ColumnDefault("''")
    @Column(name = "InvoiceDocumentaryCode", length = 30)
    private String invoiceDocumentaryCode;

    @Column(name = "InRequestCodeGeneralDeclaration")
    private String inRequestCodeGeneralDeclaration;

    @Column(name = "OutRequestCodeGeneralDeclaration")
    private String outRequestCodeGeneralDeclaration;

        @ColumnDefault("0")
    @Column(name = "Payment2ProtestStatus") 
    private Integer payment2ProtestStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2ServiceStatus") 
    private Integer payment2ServiceStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2ItineraryStatus") 
    private Integer payment2ItineraryStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2ArrivalStatus") 
    private Integer payment2ArrivalStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2DepartureStatus") 
    private Integer payment2DepartureStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2AnchorageStatus") 
    private Integer payment2AnchorageStatus=0;

        @ColumnDefault("0")
    @Column(name = "Payment2CargoStatus") 
    private Integer payment2CargoStatus=0;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}