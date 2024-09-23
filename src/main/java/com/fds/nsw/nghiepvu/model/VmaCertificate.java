package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_certificate", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaCertificate {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "CertificateCode", length = 12)
    private String certificateCode;

    @Column(name = "CertificateOrder")
    private Integer certificateOrder;

    @Column(name = "CertificateNo", length = 500)
    private String certificateNo;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "CertificateIssueDate")
    private Date certificateIssueDate;

    @Column(name = "CertificateExpiredDate")
    private Date certificateExpiredDate;

    @Column(name = "ExaminationDate")
    private Date examinationDate;

    @Column(name = "Comment", length = 500)
    private String comment;

    @Column(name = "ApprovalName", length = 500)
    private String approvalName;

    @Column(name = "IsExamined")
    private Integer isExamined;

    @Column(name = "mandatory")
    private Integer mandatory;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

}