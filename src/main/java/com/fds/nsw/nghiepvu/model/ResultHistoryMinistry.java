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
@Table(name = "result_history_ministry", schema = "dvc_hanghai_nghiepvu_clone")
public class ResultHistoryMinistry {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", nullable = false, length = 100)
    private String requestCode;

    @Column(name = "RequestState", nullable = false)
    private Integer requestState;

    @Column(name = "DocumentName", nullable = false)
    private Long documentName;

    @Column(name = "DocumentYear", nullable = false)
    private Integer documentYear;

    @Column(name = "MinistryCode", length = 20)
    private String ministryCode;

    @Column(name = "Organization", length = 250)
    private String organization;

    @Column(name = "Division", length = 250)
    private String division;

    @Column(name = "OfficerName", length = 250)
    private String officerName;

    @Column(name = "LatestDate")
    private Date latestDate;

    @Column(name = "BusinessTypeCode")
    private Integer businessTypeCode;

    @Column(name = "Response", length = 20)
    private String response;

    @Column(name = "Remarks", length = 600)
    private String remarks;

        @ColumnDefault("0")
    @Column(name = "IsDelete") 
    private Integer isDelete=0;

        @ColumnDefault("0")
    @Column(name = "MarkedAsDelete") 
    private Integer markedAsDelete=0;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 500)
    private String syncVersion;

}