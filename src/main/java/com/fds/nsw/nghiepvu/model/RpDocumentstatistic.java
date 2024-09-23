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
@Table(name = "rp_documentstatistics", schema = "dvc_hanghai_nghiepvu_clone")
public class RpDocumentstatistic {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "documentTypeCode", length = 45)
    private String documentTypeCode;

    @Column(name = "remainingNumber")
    private Long remainingNumber;

    @Column(name = "receivedNumber")
    private Long receivedNumber;

    @Column(name = "ontimeNumber")
    private Long ontimeNumber;

    @Column(name = "overtimeNumber")
    private Long overtimeNumber;

    @Column(name = "processingNumber")
    private Long processingNumber;

    @Column(name = "delayingNumber")
    private Long delayingNumber;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "status")
    private Integer status;

}