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
@Table(name = "dm_history_ministry", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryMinistry {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MinistryCode", nullable = false, length = 12)
    private String ministryCode;

    @Column(name = "MinistryName", nullable = false, length = 250)
    private String ministryName;

    @Column(name = "MinistryNameVN", nullable = false, length = 250)
    private String ministryNameVN;

    @Column(name = "MinistryOrder")
    private Integer ministryOrder;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete = 0;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 500)
    private String syncVersion;

}