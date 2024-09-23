package com.fds.nsw.nghiepvu.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_document", schema = "dvc_hanghai_nghiepvu_clone")
public class TempDocument {
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

    @Column(name = "DocumentTypeCode", length = 12)
    private String documentTypeCode;

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "ShipTypeCode", length = 18)
    private String shipTypeCode;

    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "ShipCaptain")
    private String shipCaptain;

    @Column(name = "IMO", length = 20)
    private String imo;

    @Column(name = "GRT")
    private double grt;

    @Column(name = "NT")
    private double nt;

    @Column(name = "DWT")
    private double dwt;

    @Column(name = "UnitGRT", length = 12)
    private String unitGRT;

    @Column(name = "UnitNT", length = 12)
    private String unitNT;

    @Column(name = "UnitDWT", length = 12)
    private String unitDWT;

    @Column(name = "CallSign", length = 35)
    private String callSign;

    @Column(name = "PreDocumentName", length = 20)
    private String preDocumentName;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;

    @Column(name = "FlowFlag")
    private Integer flowFlag;

    @Column(name = "DocumentStatusCode")
    private Integer documentStatusCode;

    @Column(name = "LocationCode", length = 18)
    private String locationCode;

    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "PortRegionCode", length = 18)
    private String portRegionCode;

    @Column(name = "PortCode", length = 12)
    private String portCode;

    @Column(name = "LastPortCode", length = 18)
    private String lastPortCode;

    @Column(name = "ShipPosition")
    private Integer shipPosition;

    @Column(name = "ShipOwnerCode", length = 200)
    private String shipOwnerCode;

    @Column(name = "ArrivalShipAgency", length = 18)
    private String arrivalShipAgency;

    @Column(name = "DepartureShipAgency", length = 18)
    private String departureShipAgency;

    @Column(name = "ShipDateFrom")
    private Date shipDateFrom;

    @Column(name = "ShipDateTo")
    private Date shipDateTo;

    @ColumnDefault("1")
    @Column(name = "UpgradeVersion")
    private Integer upgradeVersion;

    @Column(name = "NameOfShipownersAgents")
    private String nameOfShipownersAgents;

}