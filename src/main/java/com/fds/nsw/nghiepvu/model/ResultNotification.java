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
@Table(name = "result_notification", schema = "dvc_hanghai_nghiepvu_clone")
public class ResultNotification {
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

    @Column(name = "MaritimeCode", length = 20)
    private String maritimeCode;

    @Column(name = "Role")
    private Integer role;

    @Column(name = "Response", length = 600)
    private String response;

    @Column(name = "ResponseTime")
    private Date responseTime;

    @Column(name = "Organization", length = 250)
    private String organization;

    @Column(name = "Division", length = 250)
    private String division;

    @Column(name = "OfficerName", length = 250)
    private String officerName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "LatestDate", nullable = false)
    private Date latestDate;

    @Column(name = "BusinessTypeCode")
    private Integer businessTypeCode;

    @Column(name = "Remarks", length = 600)
    private String remarks;

    @Column(name = "IsReply")
    private Integer isReply=0;

}