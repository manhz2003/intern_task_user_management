package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_debitnote", schema = "dvc_hanghai_nghiepvu_clone")
public class TempDebitnote {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "debitnotenumber", length = 100)
    private String debitnotenumber;

    @Column(name = "organization")
    private String organization;

    @Column(name = "division")
    private String division;

    @Column(name = "reportby")
    private String reportby;

    @Column(name = "reportdate")
    private Date reportdate;

    @Column(name = "fromdate")
    private Date fromdate;

    @Column(name = "todate")
    private Date todate;

    @Column(name = "totalpayment", precision = 20, scale = 3)
    private BigDecimal totalpayment;

    @Column(name = "currency", nullable = false, length = 75)
    private String currency;

    @ColumnDefault("0.000")
    @Column(name = "totalforeignpayment", precision = 20, scale = 3)
    private BigDecimal totalforeignpayment;

    @ColumnDefault("''")
    @Column(name = "foreigncurrency", length = 30)
    private String foreigncurrency;

    @Column(name = "paymenttype")
    private Integer paymenttype;

        @ColumnDefault("0")
    @Column(name = "markasdeleted") 
    private Integer markasdeleted=0;

    @Column(name = "transactionid", length = 100)
    private String transactionid;

    @Column(name = "corporationid", length = 30)
    private String corporationid;

    @Column(name = "financialaccountant")
    private String financialaccountant;

    @Column(name = "ATTACHEDFILE")
    private Long attachedfile;

    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "mariTimeCode", nullable = false)
    private String mariTimeCode;

    @Column(name = "comments", nullable = false, length = 2000)
    private String comments;

    @Column(name = "documentTypeCode")
    private String documentTypeCode;

    @Column(name = "keypayGoodCode")
    private String keypayGoodCode;

    @Column(name = "keypayMerchantCode")
    private String keypayMerchantCode;

    @Column(name = "paymentGateStatusCode")
    private String paymentGateStatusCode;

    @Lob
    @Column(name = "paymentGateResponseData")
    private String paymentGateResponseData;

    @Column(name = "paymentGateCheckCode")
    private String paymentGateCheckCode;

    @Lob
    @Column(name = "paymentGateCheckResponseData")
    private String paymentGateCheckResponseData;

    @Lob
    @Column(name = "keypayURL")
    private String keypayURL;

    @Lob
    @Column(name = "description")
    private String description;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modifiedDate")
    private Date modifiedDate;

}