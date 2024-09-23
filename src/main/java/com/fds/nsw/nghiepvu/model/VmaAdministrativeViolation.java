package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_administrative_violation", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaAdministrativeViolation {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "ViolationCode", length = 30)
    private String violationCode;

    @Lob
    @Column(name = "ViolationBrief")
    private String violationBrief;

    @Lob
    @Column(name = "DamagedPartName")
    private String damagedPartName;

    @Lob
    @Column(name = "Conclusion")
    private String conclusion;

    @Lob
    @Column(name = "MetaData")
    private String metaData;

    @Column(name = "PaymentAmount")
    private String paymentAmount;

    @Column(name = "AmountInWords")
    private String amountInWords;

    @Column(name = "DocumentaryNo", length = 30)
    private String documentaryNo;

    @Column(name = "DocumentaryDate")
    private Date documentaryDate;

    @Column(name = "PaymentDate")
    private Date paymentDate;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber", length = 30)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Column(name = "DecisionNo", length = 50)
    private String decisionNo;

    @Column(name = "DecisionDate")
    private Date decisionDate;

    @Column(name = "DecisionOrganization")
    private String decisionOrganization;

    @Column(name = "OfficialNo", length = 50)
    private String officialNo;

    @Column(name = "OfficialDate")
    private Date officialDate;

    @Column(name = "OfficialPlace")
    private String officialPlace;

    @Column(name = "ViolationDate")
    private Date violationDate;

    @Column(name = "ViolationPartCode", length = 50)
    private String violationPartCode;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "IssueBy")
    private String issueBy;

    @Column(name = "ViolationPartName")
    private String violationPartName;

    @Column(name = "ViolationPartAddress")
    private String violationPartAddress;

    @Column(name = "Representative")
    private String representative;

    @Column(name = "RepresentativeTitle", length = 100)
    private String representativeTitle;

    @Column(name = "f1AttachedReport", length = 100)
    private String f1AttachedReport;

    @Column(name = "f2AttachedReport", length = 100)
    private String f2AttachedReport;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}