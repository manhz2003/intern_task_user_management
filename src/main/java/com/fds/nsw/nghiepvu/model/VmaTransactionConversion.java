package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_conversion", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionConversion {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ShipTypeMT", length = 12)
    private String shipTypeMT;

    @Column(name = "ShipTypeCode", length = 12)
    private String shipTypeCode;

    @Column(name = "FunctionType", length = 20)
    private String functionType;

    @Lob
    @Column(name = "FunctionName")
    private String functionName;

    @ColumnDefault("0.00")
    @Column(name = "ConversionRate")
    private Double conversionRate;

    @Column(name = "ConversionUnit", length = 50)
    private String conversionUnit;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}