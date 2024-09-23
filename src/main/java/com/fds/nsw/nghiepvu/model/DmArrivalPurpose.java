package com.fds.nsw.nghiepvu.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dm_arrival_purpose", schema = "dvc_hanghai_nghiepvu_clone")
public class DmArrivalPurpose {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PurposeCode", length = 12)
    private String purposeCode;

    @Column(name = "PurposeName", length = 50)
    private String purposeName;

    @Column(name = "PurposeNameVN", length = 50)
    private String purposeNameVN;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "requestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}