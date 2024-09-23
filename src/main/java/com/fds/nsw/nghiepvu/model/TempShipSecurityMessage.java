package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_ship_security_message", schema = "dvc_hanghai_nghiepvu_clone")
public class TempShipSecurityMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id = 0L;

    @Column(name = "RequestCode")
    private String requestCode;

    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "ShipSecurityCode", length = 50)
    private String shipSecurityCode;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "ShipAgencyCode", length = 20)
    private String shipAgencyCode;

    @Column(name = "NameOfShipAgent")
    private String nameOfShipAgent;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "ShipTypeCode", length = 12)
    private String shipTypeCode;

    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "ShipCaptain", length = 100)
    private String shipCaptain;

    @Column(name = "IMO", length = 50)
    private String imo;

    @Column(name = "GRT")
    private double grt;

    @Column(name = "UnitGRT", length = 12)
    private String unitGRT;

    @Column(name = "CrewNumber", precision = 18)
    private double crewNumber;

    @Column(name = "ArrivalDate")
    private Date arrivalDate;

    @Column(name = "PurposeCode", length = 12)
    private String purposeCode;

    @Column(name = "PurposeSpecified", length = 200)
    private String purposeSpecified;

    @Column(name = "PortRegionCode", length = 12)
    private String portRegionCode;

    @Column(name = "PortWharfCode", length = 12)
    private String portWharfCode;

    @Column(name = "ShipAgencyPhone", length = 100)
    private String shipAgencyPhone;

    @Column(name = "ShipAgencyFax", length = 100)
    private String shipAgencyFax;

    @Column(name = "IsShipSecurity")
    private Integer isShipSecurity;

    @Column(name = "NameOfISSC", length = 200)
    private String nameOfISSC;

    @Column(name = "DateOfISSC")
    private Date dateOfISSC;

    @Column(name = "DateOfExpiryISSC")
    private Date dateOfExpiryISSC;

    @Column(name = "SecurityLevelCode", length = 12)
    private String securityLevelCode;

    @Column(name = "SecurityFromDate")
    private Instant securityFromDate;

    @Column(name = "IsAdditionalSecurityMeasures")
    private Integer isAdditionalSecurityMeasures;

    @Column(name = "AdditionalSecurityDetail", length = 500)
    private String additionalSecurityDetail;

    @Column(name = "IsMaintainSecurity")
    private Integer isMaintainSecurity;

    @Column(name = "MaintainSecurityDetail", length = 500)
    private String maintainSecurityDetail;

    @Column(name = "Latitude", length = 50)
    private String latitude;

    @Column(name = "Longitude", length = 50)
    private String longitude;

    @Column(name = "HasShipSecurityPortItems")
    private Integer hasShipSecurityPortItems;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage")
    private byte[] masterSignedImage;

    @Column(name = "CallSign")
    private String callSign;

    @Column(name = "ArrivalPortCode", length = 18)
    private String arrivalPortCode;

    @Column(name = "PortHarbourCode", length = 18)
    private String portHarbourCode;

}