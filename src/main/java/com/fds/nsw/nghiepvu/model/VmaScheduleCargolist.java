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
@Table(name = "vma_schedule_cargolist", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleCargolist {
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

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "NoticeShipType")
    private Integer noticeShipType;

    @Column(name = "PortRegionCode", length = 5)
    private String portRegionCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @ColumnDefault("''")
    @Column(name = "PortWharfCode", length = 6)
    private String portWharfCode;

    @Column(name = "UnloadingDate")
    private Date unloadingDate;

        @ColumnDefault("0")
    @Column(name = "DangerCargo") 
    private Integer dangerCargo=0;

    @Column(name = "CargoCategory", length = 6)
    private String cargoCategory;

    @Column(name = "CargoType", length = 6)
    private String cargoType;

    @Column(name = "CargoCode")
    private String cargoCode;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "Quantity", precision = 18, scale = 2)
    private BigDecimal quantity;

    @Column(name = "Unit", length = 12)
    private String unit;

    @ColumnDefault("0.00")
    @Column(name = "TeusQuantity", precision = 18, scale = 2)
    private BigDecimal teusQuantity;

    @ColumnDefault("0.00")
    @Column(name = "ContQuantity", precision = 18, scale = 2)
    private BigDecimal contQuantity;

        @ColumnDefault("0")
    @Column(name = "MakePayment") 
    private Integer makePayment=0;

    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @ColumnDefault("0")
    @Column(name = "ScheduleAnchorageId")
    private Long scheduleAnchorageId;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}