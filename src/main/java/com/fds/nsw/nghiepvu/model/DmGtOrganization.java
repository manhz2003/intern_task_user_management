package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_gt_organization", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtOrganization {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "OrganizationCode", nullable = false, length = 12)
    private String organizationCode;

    @Column(name = "OrganizationName", nullable = false, length = 250)
    private String organizationName;

    @Column(name = "OrganizationNameVN", nullable = false, length = 250)
    private String organizationNameVN;

    @Column(name = "OrganizationOrder")
    private Integer organizationOrder;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete = 0;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 600)
    private String syncVersion;

}