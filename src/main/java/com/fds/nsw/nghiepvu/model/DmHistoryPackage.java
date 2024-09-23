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
@Table(name = "dm_history_package", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryPackage {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PackageCode", nullable = false, length = 10)
    private String packageCode;

    @Column(name = "PackageName", nullable = false, length = 250)
    private String packageName;

    @Column(name = "PackageNameVN", nullable = false, length = 250)
    private String packageNameVN;

    @Column(name = "PackageOrder")
    private Integer packageOrder;

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

}