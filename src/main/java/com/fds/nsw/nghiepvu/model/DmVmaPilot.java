package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_vma_pilot", schema = "dvc_hanghai_nghiepvu_clone")
public class DmVmaPilot {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "PilotCompanyCode", length = 75)
    private String pilotCompanyCode;

    @Lob
    @Column(name = "PilotCompanyName")
    private String pilotCompanyName;

    @Column(name = "PilotCode", length = 75)
    private String pilotCode;

    @ColumnDefault("''")
    @Column(name = "PilotName")
    private String pilotName;

    @Column(name = "PilotShortName", length = 75)
    private String pilotShortName;

    @Column(name = "PilotBOD", length = 75)
    private String pilotBOD;

    @Column(name = "PilotNo", length = 75)
    private String pilotNo;

    @Column(name = "PilotCertificateNo", length = 75)
    private String pilotCertificateNo;

    @Column(name = "PilotCertificateDate")
    private Date pilotCertificateDate;

    @Column(name = "PilotExpiredDate")
    private Date pilotExpiredDate;

    @Column(name = "PilotCategoryCode", length = 75)
    private String pilotCategoryCode;

    @Column(name = "Remarks", length = 75)
    private String remarks;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 75)
    private String syncVersion;

}