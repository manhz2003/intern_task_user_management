package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_shipyard", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleShipyard {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

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

    @Column(name = "ShipYardCode", length = 30)
    private String shipYardCode;

    @Column(name = "ShipYardCompanyName")
    private String shipYardCompanyName;

    @Column(name = "ShipYardOfficialNo", length = 30)
    private String shipYardOfficialNo;

    @Column(name = "RepairingFrom")
    private Date repairingFrom;

    @Column(name = "RepairingTo")
    private Date repairingTo;

    @Column(name = "RepairingPlace")
    private String repairingPlace;

    @Lob
    @Column(name = "RepairingReason")
    private String repairingReason;

    @Column(name = "Repaired")
    private Integer repaired;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "DirectorOfMaritime", length = 100)
    private String directorOfMaritime;

    @Column(name = "CertificateNo", length = 100)
    private String certificateNo;

    @ColumnDefault("1")
    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "VersionNo", length = 6)
    private String versionNo;

    @Column(name = "IsApproval")
    private Integer isApproval;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ApprovalDate")
    private Date approvalDate;

    @Column(name = "ApprovalName", length = 100)
    private String approvalName;

    @Column(name = "Remarks", length = 600)
    private String remarks;

    @Column(name = "IsCancel")
    private Integer isCancel;

    @Column(name = "CancelDate")
    private Date cancelDate;

    @Column(name = "CancelName", length = 100)
    private String cancelName;

    @ColumnDefault("'System'")
    @Column(name = "CancelNote", length = 600)
    private String cancelNote;

    @ColumnDefault("'GIÁM ĐỐC'")
    @Column(name = "Representative", length = 600)
    private String representative;

        @ColumnDefault("0")
    @Column(name = "DigitalAttachedFile") 
    private Integer digitalAttachedFile=0;

    @ColumnDefault("''")
    @Column(name = "SignName", length = 600)
    private String signName;

    @ColumnDefault("''")
    @Column(name = "SignTitle", length = 600)
    private String signTitle;

    @Column(name = "SignDate")
    private Date signDate;

    @ColumnDefault("''")
    @Column(name = "SignPlace", length = 600)
    private String signPlace;

    @ColumnDefault("0")
    @Column(name = "AttachedFile")
    private Long attachedFile;

        @ColumnDefault("0")
    @Column(name = "StampStatus") 
    private Integer stampStatus=0;

    @ColumnDefault("''")
    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @ColumnDefault("''")
    @Column(name = "ShipOwnersName")
    private String shipOwnersName;

    @ColumnDefault("''")
    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @ColumnDefault("''")
    @Column(name = "ShipOperatorName")
    private String shipOperatorName;

    @ColumnDefault("''")
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
    @Column(name = "SecurityLevelCode", length = 50)
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