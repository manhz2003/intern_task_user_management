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
@Table(name = "vma_payment_advance", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaPaymentAdvance {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @ColumnDefault("''")
    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @ColumnDefault("''")
    @Column(name = "NameOfShip")
    private String nameOfShip;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @ColumnDefault("''")
    @Column(name = "ShipOwnerName")
    private String shipOwnerName;

    @Column(name = "ShipCaptain")
    private String shipCaptain;

    @ColumnDefault("-1")
    @Column(name = "AmtRate")
    private Integer amtRate;

    @ColumnDefault("0.00")
    @Column(name = "Amount")
    private Double amount;

        @ColumnDefault("0")
    @Column(name = "PaymentType") 
    private Integer paymentType=0;

    @ColumnDefault("''")
    @Column(name = "TransactionTypeCode", length = 12)
    private String transactionTypeCode;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}