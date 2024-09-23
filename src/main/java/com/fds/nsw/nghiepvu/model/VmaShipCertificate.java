package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_ship_certificate", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaShipCertificate {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "mtgateway")
    private Integer mtgateway;

    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "ShipId")
    private Integer shipId;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "VRCode", length = 20)
    private String vrCode;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Column(name = "CertificateCode", length = 20)
    private String certificateCode;

    @Column(name = "CertificateName", length = 500)
    private String certificateName;

    @Column(name = "CertificateOrder")
    private Integer certificateOrder;

    @Column(name = "CertificateNo", length = 50)
    private String certificateNo;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "CertificateIssueDate")
    private Date certificateIssueDate;

    @Column(name = "CertificateExpiredDate")
    private Date certificateExpiredDate;

    @Column(name = "ExaminationDate")
    private Date examinationDate;

    @Lob
    @Column(name = "Comment")
    private String comment;

    @Column(name = "IsExamined")
    private Integer isExamined;

    @Column(name = "Mandatory")
    private Integer mandatory;

}