package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "result_completion", schema = "dvc_hanghai_nghiepvu_clone")
public class ResultCompletion {
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

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "NameOfMaster")
    private String nameOfMaster;

    @Column(name = "GrossTonnage", precision = 10, scale = 2)
    private BigDecimal grossTonnage;

    @Column(name = "PortOfArrivalCode", length = 5)
    private String portOfArrivalCode;

    @Column(name = "MaritimeCode", length = 8)
    private String maritimeCode;

    @Column(name = "Division", length = 250)
    private String division;

    @Column(name = "ApprovalName", length = 250)
    private String approvalName;

    @Column(name = "ApprovalTime")
    private Date approvalTime;

    @Column(name = "CertificateNo", length = 250)
    private String certificateNo;

    @Column(name = "ResponseStatusHQ")
    private Integer responseStatusHQ;

    @Column(name = "ResponseStatusBP")
    private Integer responseStatusBP;

    @Column(name = "ResponseStatusYT")
    private Integer responseStatusYT;

    @Column(name = "ResponseStatusDV")
    private Integer responseStatusDV;

    @Column(name = "ResponseStatusTV")
    private Integer responseStatusTV;

    @Column(name = "ResponseStatusCVHH")
    private Integer responseStatusCVHH;

    @Column(name = "ResponseTimeHQ")
    private Date responseTimeHQ;

    @Column(name = "ResponseTimeBP")
    private Date responseTimeBP;

    @Column(name = "ResponseTimeYT")
    private Date responseTimeYT;

    @Column(name = "ResponseTimeDV")
    private Date responseTimeDV;

    @Column(name = "ResponseTimeTV")
    private Date responseTimeTV;

    @Column(name = "ResponseTimeCVHH")
    private Date responseTimeCVHH;

    @Column(name = "ResponseHQ", length = 575)
    private String responseHQ;

    @Column(name = "ResponseBP", length = 575)
    private String responseBP;

    @Column(name = "ResponseYT", length = 575)
    private String responseYT;

    @Column(name = "ResponseDV", length = 575)
    private String responseDV;

    @Column(name = "ResponseTV", length = 575)
    private String responseTV;

    @Column(name = "ResponseCVHH", length = 575)
    private String responseCVHH;

}