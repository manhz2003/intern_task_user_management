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
@Table(name = "dm_gt_report_template", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtReportTemplate {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ReportCode", nullable = false)
    private Integer reportCode;

    @Column(name = "ReportName", nullable = false, length = 250)
    private String reportName;

    @Column(name = "ReportType")
    private Integer reportType;

    @Column(name = "Category", length = 12)
    private String category;

    @Column(name = "Description", length = 250)
    private String description;

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