package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_security", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleSecurity {
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

    @Column(name = "SecurityOfficeCode", length = 30)
    private String securityOfficeCode;

    @Column(name = "SecurityCompanyName")
    private String securityCompanyName;

    @Column(name = "SecurityOfficialNo", length = 30)
    private String securityOfficialNo;

    @Column(name = "SecurityDate")
    private Date securityDate;

    @Column(name = "SecurityPlace")
    private String securityPlace;

    @Lob
    @Column(name = "SecurityReason")
    private String securityReason;

    @Column(name = "Evacuated")
    private Integer evacuated;

    @Column(name = "EvacuateOfficialCode", length = 30)
    private String evacuateOfficialCode;

    @Column(name = "EvacuateCompanyName")
    private String evacuateCompanyName;

    @Column(name = "EvacuateOfficialNo", length = 30)
    private String evacuateOfficialNo;

    @Column(name = "EvacuateDate")
    private Date evacuateDate;

    @Lob
    @Column(name = "EvacuateReason")
    private String evacuateReason;

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