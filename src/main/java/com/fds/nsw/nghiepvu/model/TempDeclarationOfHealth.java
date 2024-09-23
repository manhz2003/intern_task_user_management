package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_declaration_of_health", schema = "dvc_hanghai_nghiepvu_clone")
public class TempDeclarationOfHealth {
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

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "SubmittedPortCode", length = 5)
    private String submittedPortCode;

    @Column(name = "DateSubmitted")
    private Date dateSubmitted;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "Registration", length = 250)
    private String registration;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "ArrivingFrom", length = 5)
    private String arrivingFrom;

    @Column(name = "SailingTo", length = 5)
    private String sailingTo;

    @Column(name = "Nationality", length = 2)
    private String nationality;

    @Column(name = "MasterName")
    private String masterName;

    @Column(name = "GrossTonnage")
    private double grossTonnage;

    @Column(name = "GrossTonnageUnit", length = 20)
    private String grossTonnageUnit;

    @Column(name = "Tonnage")
    private double tonnage;

    @Column(name = "TonnageUnit", length = 12)
    private String tonnageUnit;

    @Column(name = "CertificateCarried")
    private Integer certificateCarried;

    @Column(name = "IssuedAt")
    private String issuedAt;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "ReInspectionRequired")
    private Integer reInspectionRequired;

    @Column(name = "IsShipVisitedWHO")
    private Integer isShipVisitedWHO;

    @Column(name = "VisitedWHOPortCode", length = 5)
    private String visitedWHOPortCode;

    @Column(name = "DateOfVisitedWHO")
    private Date dateOfVisitedWHO;

    @Column(name = "ListPortName", length = 500)
    private String listPortName;

    @Column(name = "DoctorName")
    private String doctorName;

    @Column(name = "DoctorSignDate")
    private Date doctorSignDate;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage", length = 1)
    private String masterSignedImage;

    @Column(name = "AttachedFile")
    private String attachedFile;

}