package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_slip_function", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionSlipFunction {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "DocumentaryCode", length = 50)
    private String documentaryCode;

    @Column(name = "NameOfShip", length = 50)
    private String nameOfShip;

    @Column(name = "TransactionTypeCode", length = 30)
    private String transactionTypeCode;

    @Column(name = "FunctionCode", length = 30)
    private String functionCode;

    @Column(name = "ChargeType")
    private Integer chargeType;

    @Lob
    @Column(name = "FunctionNote")
    private String functionNote;

    @Lob
    @Column(name = "TransactionNote")
    private String transactionNote;

    @Column(name = "ChargeRate")
    private Double chargeRate;

    @Column(name = "DiscountRate")
    private Double discountRate;

}