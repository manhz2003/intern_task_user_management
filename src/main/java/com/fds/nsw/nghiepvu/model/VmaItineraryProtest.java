package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_itinerary_protest", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaItineraryProtest {
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

    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @ColumnDefault("''")
    @Column(name = "ShipOwnersName")
    private String shipOwnersName;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @ColumnDefault("''")
    @Column(name = "ShipOperatorName")
    private String shipOperatorName;

    @Column(name = "ProtestDate")
    private Date protestDate;

    @Column(name = "TroubleShootingDate")
    private Date troubleShootingDate;

    @Column(name = "FinishedDate")
    private Date finishedDate;

    @Lob
    @Column(name = "ProtestRemarks")
    private String protestRemarks;

        @ColumnDefault("0")
    @Column(name = "MakePayment") 
    private Integer makePayment=0;

    @ColumnDefault("''")
    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyAddress", length = 575)
    private String shipAgencyAddress;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyContactEmail", length = 575)
    private String shipAgencyContactEmail;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyPhone", length = 575)
    private String shipAgencyPhone;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyFax", length = 575)
    private String shipAgencyFax;

    @ColumnDefault("''")
    @Column(name = "SecurityLevelCode", length = 10)
    private String securityLevelCode;

    @ColumnDefault("''")
    @Column(name = "DepartmentCode", length = 30)
    private String departmentCode;

    @ColumnDefault("''")
    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}