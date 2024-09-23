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
@Table(name = "dm_gt_business_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtBusinessType {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "BusinessTypeCode", nullable = false)
    private Integer businessTypeCode;

    @Column(name = "BusinessTypeName", nullable = false, length = 250)
    private String businessTypeName;

    @Column(name = "BusinessTypeNameVN", nullable = false, length = 250)
    private String businessTypeNameVN;

    @Column(name = "BusinessTypeOrder")
    private Integer businessTypeOrder;

    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete;

    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

    @Column(name = "Remarks", length = 2000)
    private String remarks;

}