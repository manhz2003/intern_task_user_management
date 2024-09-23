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
@Table(name = "result_declaration", schema = "dvc_hanghai_nghiepvu_clone")
public class ResultDeclaration {
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

    @Column(name = "BusinessOrder")
    private Integer businessOrder;

    @Column(name = "BusinessTypeCode")
    private Integer businessTypeCode;

    @Column(name = "LatestSend")
    private Integer latestSend;

    @Column(name = "DeclarationTime")
    private Date declarationTime;

    @Column(name = "ArrivalDepartureTime")
    private Date arrivalDepartureTime;

    @Column(name = "RemainingTime")
    private String remainingTime;

    @Column(name = "Remarks", length = 600)
    private String remarks;

}