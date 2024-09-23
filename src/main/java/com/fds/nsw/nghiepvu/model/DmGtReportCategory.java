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
@Table(name = "dm_gt_report_category", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtReportCategory {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "Category", length = 12)
    private String category;

    @Column(name = "CategoryName", nullable = false, length = 250)
    private String categoryName;

    @Column(name = "CategoryOrder")
    private Integer categoryOrder;

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