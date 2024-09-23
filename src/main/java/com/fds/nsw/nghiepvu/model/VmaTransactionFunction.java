package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_function", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionFunction {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "TransactionTypeCode", length = 20)
    private String transactionTypeCode;

    @Column(name = "FunctionCode", length = 20)
    private String functionCode;

    @Lob
    @Column(name = "FunctionName")
    private String functionName;

    @Column(name = "MakePayment")
    private Integer makePayment;

    @Column(name = "ForArrival")
    private Integer forArrival;

    @Column(name = "ForDeparture")
    private Integer forDeparture;

    @Column(name = "ChargeType")
    private Integer chargeType;

    @Lob
    @Column(name = "FunctionNote")
    private String functionNote;

    @Lob
    @Column(name = "TransactionNote")
    private String transactionNote;

    @Lob
    @Column(name = "ChargeConditions")
    private String chargeConditions;

    @Column(name = "ChargeRate")
    private Double chargeRate;

    @Column(name = "DiscountRate")
    private Double discountRate;

    @Column(name = "DiscountType1")
    private Integer discountType1;

    @Column(name = "DiscountType2")
    private Integer discountType2;

    @Column(name = "DiscountType3")
    private Integer discountType3;

    @Column(name = "DiscountType4")
    private Integer discountType4;

    @Column(name = "DiscountType5")
    private Integer discountType5;

    @Column(name = "PurposeCode", length = 12)
    private String purposeCode;

    @Column(name = "ShipTypeCode", length = 12)
    private String shipTypeCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "applied", length = 30)
    private String applied;

    @Column(name = "appliedFrom")
    private Date appliedFrom;

    @Column(name = "appliedTo")
    private Date appliedTo;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "AnchorageHourFrom")
    private Double anchorageHourFrom;

    @Column(name = "AnchorageHourTo")
    private Double anchorageHourTo;

    @Column(name = "dwtFrom")
    private Double dwtFrom;

    @Column(name = "dwtTo")
    private Double dwtTo;

    @Column(name = "gtFrom")
    private Double gtFrom;

    @Column(name = "gtTo")
    private Double gtTo;

    @Column(name = "SeatFrom")
    private Double seatFrom;

    @Column(name = "SeatTo")
    private Double seatTo;

    @Column(name = "voyageNumFrom")
    private Double voyageNumFrom;

}