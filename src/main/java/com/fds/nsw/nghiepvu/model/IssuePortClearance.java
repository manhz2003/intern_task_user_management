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
@Table(name = "issue_port_clearance", schema = "dvc_hanghai_nghiepvu_clone")
public class IssuePortClearance {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "NumberPortClearance", length = 30)
    private String numberPortClearance;

    @Column(name = "DocumentName")
    private long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "PortofAuthority", length = 100)
    private String portofAuthority;

    @Column(name = "NameOfShip", length = 100)
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "NumberOfCrews")
    private Integer numberOfCrews;

    @Column(name = "NumberOfPassengers")
    private Integer numberOfPassengers;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "NameOfMaster", length = 100)
    private String nameOfMaster;

    @Column(name = "Cargo", length = 50)
    private String cargo;

    @Column(name = "VolumeCargo")
    private double volumeCargo;

    @Column(name = "CargoUnit", length = 50)
    private String cargoUnit;

    @Lob
    @Column(name = "CargoDescription")
    private String cargoDescription;

    @Column(name = "TransitCargo", length = 50)
    private String transitCargo;

    @Column(name = "VolumeTransitCargo")
    private double volumeTransitCargo;

    @Column(name = "TransitCargoUnit", length = 50)
    private String transitCargoUnit;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @Column(name = "NextPortOfCallCode", length = 52)
    private String nextPortOfCallCode;

    @Column(name = "NextProvinceCode", length = 5)
    private String nextProvinceCode;

    @Column(name = "MaritimePortCode", length = 20)
    private String maritimePortCode;

    @Column(name = "PortCode", length = 100)
    private String portCode;

    @Column(name = "PortHarbourCode", length = 20)
    private String portHarbourCode;

    @Column(name = "ValidUntil")
    private Date validUntil;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "DirectorOfMaritime", length = 100)
    private String directorOfMaritime;

    @Column(name = "CertificateNo", length = 100)
    private String certificateNo;

    @Column(name = "GT")
    private double gt;

    @Column(name = "DWT")
    private double dwt;

    @Column(name = "NextMaritimePortCode", length = 20)
    private String nextMaritimePortCode;

    @Column(name = "NextPortRegionCode", length = 20)
    private String nextPortRegionCode;

    @Column(name = "NextPortHarbourCode", length = 20)
    private String nextPortHarbourCode;

    @Column(name = "NextPortWharfCode", length = 20)
    private String nextPortWharfCode;

    @ColumnDefault("1")
    @Column(name = "RequestState", nullable = false)
    private Integer requestState;

    @Column(name = "VersionNo", length = 4)
    private String versionNo;

    @Column(name = "IsApproval")
    private Integer isApproval;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ApprovalDate", nullable = false)
    private Date approvalDate;

    @Column(name = "ApprovalName", length = 100)
    private String approvalName;

    @Column(name = "Remarks", length = 600)
    private String remarks;

    @Column(name = "IsCancel")
    private Integer isCancel;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "CancelDate", nullable = false)
    private Date cancelDate;

    @Column(name = "CancelName", length = 100)
    private String cancelName;

    @Column(name = "CancelNote", length = 600)
    private String cancelNote;

    @Column(name = "Representative", length = 600)
    private String representative;

        @ColumnDefault("0")
    @Column(name = "DigitalAttachedFile") 
    private Integer digitalAttachedFile=0;

    @Column(name = "SignName", length = 600)
    private String signName;

    @Column(name = "SignTitle", length = 600)
    private String signTitle;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "SignPlace", length = 600)
    private String signPlace;

    @Column(name = "AttachedFile")
    private Long attachedFile;

    @Column(name = "StampStatus")
    private Integer stampStatus;

}