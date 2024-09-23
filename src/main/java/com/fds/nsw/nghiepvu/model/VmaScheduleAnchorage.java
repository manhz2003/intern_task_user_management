package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_anchorage", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleAnchorage {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "PurposeCode", length = 75)
    private String purposeCode;

    @ColumnDefault("''")
    @Column(name = "PurposeSpecified")
    private String purposeSpecified;

    @Column(name = "AnchoringDateFrom")
    private Date anchoringDateFrom;

    @Column(name = "AnchoringDateTo")
    private Date anchoringDateTo;

    @ColumnDefault("0.00")
    @Column(name = "AnchoringDuration")
    private Double anchoringDuration;

    @ColumnDefault("0.00")
    @Column(name = "AnchorageFreeDuration")
    private Double anchorageFreeDuration;

    @ColumnDefault("0.00")
    @Column(name = "AnchorageDomesticDuration")
    private Double anchorageDomesticDuration;

    @ColumnDefault("0.00")
    @Column(name = "AnchorageForeignDuration")
    private Double anchorageForeignDuration;

    @Column(name = "AnchoringPortRegionCode", length = 5)
    private String anchoringPortRegionCode;

    @Column(name = "AnchoringPortHarbourCode", length = 12)
    private String anchoringPortHarbourCode;

    @Column(name = "AnchoringPortWharfCode", length = 6)
    private String anchoringPortWharfCode;

        @ColumnDefault("0")
    @Column(name = "NoticeShipType") 
    private Integer noticeShipType=0;

    @Column(name = "PortRegionCode", length = 5)
    private String portRegionCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "PortWharfCode", length = 6)
    private String portWharfCode;

    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @ColumnDefault("''")
    @Column(name = "SecurityLevelCode", length = 50)
    private String securityLevelCode;

    @Lob
    @Column(name = "AnchorageFreeDurationRemarks")
    private String anchorageFreeDurationRemarks;

    @Column(name = "MakePayment")
    private Integer makePayment;

    @Column(name = "AnchorageSupplement")
    private Integer anchorageSupplement;

    @ColumnDefault("''")
    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}