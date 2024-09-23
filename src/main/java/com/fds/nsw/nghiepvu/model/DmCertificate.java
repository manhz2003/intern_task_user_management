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
@Table(name = "dm_certificate", schema = "dvc_hanghai_nghiepvu_clone")
public class DmCertificate {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "CertificateCode", nullable = false, length = 12)
    private String certificateCode;

    @Column(name = "CertificateName", nullable = false, length = 250)
    private String certificateName;

    @Column(name = "CertificateNameVN", nullable = false, length = 250)
    private String certificateNameVN;

    @Column(name = "CertificateOrder")
    private Integer certificateOrder;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 600)
    private String syncVersion;

}