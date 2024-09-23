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
@Table(name = "vma_schedule_tugboat_list", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleTugboatList {
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

    @Column(name = "TugboatCompanyCode", length = 30)
    private String tugboatCompanyCode;

    @Column(name = "TugboatCompanyName")
    private String tugboatCompanyName;

    @Column(name = "ShipCode", length = 30)
    private String shipCode;

    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "Power")
    private Double power;

    @Column(name = "UnitPower", length = 6)
    private String unitPower;

    @Column(name = "GT", precision = 20, scale = 2)
    private BigDecimal gt;

    @Column(name = "UnitGRT", length = 10)
    private String unitGRT;

    @ColumnDefault("'1'")
    @Column(name = "TugMode", length = 6)
    private String tugMode;

    @Column(name = "TugboatShortName", length = 30)
    private String tugboatShortName;

        @ColumnDefault("0")
    @Column(name = "MakePayment") 
    private Integer makePayment=0;

    @ColumnDefault("''")
    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

    @ColumnDefault("''")
    @Column(name = "InvoiceDocumentaryCode", length = 30)
    private String invoiceDocumentaryCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}