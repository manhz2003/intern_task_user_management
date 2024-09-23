package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_payment_invoice", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaPaymentInvoice {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "documentaryCode", length = 30)
    private String documentaryCode;

    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Lob
    @Column(name = "tugboatDescription")
    private String tugboatDescription;

    @Column(name = "unitPrice")
    private Double unitPrice;

    @Column(name = "totalhr")
    private Double totalhr;

    @Column(name = "currency", length = 5)
    private String currency;

    @Column(name = "currencyExgDate")
    private Date currencyExgDate;

    @Column(name = "exchangeRate")
    private Double exchangeRate;

    @Column(name = "tugboatFee")
    private Double tugboatFee;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "paymentAmount")
    private Double paymentAmount;

    @Column(name = "debitnoteid")
    private Long debitnoteid;

    @Column(name = "ShipAgencyCode")
    private String shipAgencyCode;

    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @Column(name = "Address")
    private String address;

    @Column(name = "PaymentStatus")
    private Integer paymentStatus;

    @Column(name = "DepartmentCode")
    private String departmentCode;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}