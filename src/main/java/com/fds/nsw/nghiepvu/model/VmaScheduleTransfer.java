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
@Table(name = "vma_schedule_transfer", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleTransfer {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "mtgateway")
    private Integer mtgateway;

    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "ShipPreviousName")
    private String shipPreviousName;

    @Column(name = "ShipTypeMT", length = 20)
    private String shipTypeMT;

    @Column(name = "ShipTypeCode", length = 20)
    private String shipTypeCode;

    @Column(name = "ShipBoat", length = 20)
    private String shipBoat;

    @Column(name = "HasTugBoat")
    private Integer hasTugBoat;

    @Column(name = "TugBoatName")
    private String tugBoatName;

    @Column(name = "NameOfMaster")
    private String nameOfMaster;

    @Column(name = "ChiefOfEngineer")
    private String chiefOfEngineer;

    @Column(name = "CertificateOfMaster", length = 50)
    private String certificateOfMaster;

    @Column(name = "CertificateChiefOfEngineer", length = 50)
    private String certificateChiefOfEngineer;

    @Column(name = "SecurityLevelCode", length = 50)
    private String securityLevelCode;

    @Column(name = "Violated")
    private Integer violated;

    @Column(name = "Deconstructed")
    private Integer deconstructed;

    @Column(name = "Seat")
    private Integer seat;

    @Column(name = "Lies")
    private Integer lies;

    @Column(name = "ConstructionShipyardCode")
    private String constructionShipyardCode;

    @Column(name = "DeconstructionShipyardCode")
    private String deconstructionShipyardCode;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "VRCode", length = 20)
    private String vrCode;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Column(name = "RegistryDate")
    private Date registryDate;

    @Column(name = "RegistryPortCode", length = 20)
    private String registryPortCode;

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

    @Column(name = "UnitPower", length = 6)
    private String unitPower;

    @Column(name = "ProductionCountry")
    private String productionCountry;

    @Column(name = "ProductionYear", length = 30)
    private String productionYear;

    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @Column(name = "ExpiredDate")
    private Date expiredDate;

    @Lob
    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

        @ColumnDefault("0")
    @Column(name = "NoticeShipType") 
    private Integer noticeShipType=0;

    @Column(name = "debitnoteid")
    private Integer debitnoteid;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}