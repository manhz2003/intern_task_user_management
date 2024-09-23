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
@Table(name = "vma_transaction_balance", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionBalance {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @Column(name = "TransactionLevel")
    private Integer transactionLevel;

    @Column(name = "TransactionTypeCode", length = 12)
    private String transactionTypeCode;

    @Column(name = "TransactionTypeName", length = 275)
    private String transactionTypeName;

    @Column(name = "CurrencyCode", length = 6)
    private String currencyCode;

    @Column(name = "SttlmtAmount")
    private Double sttlmtAmount;

    @Column(name = "DepartmentCode", length = 12)
    private String departmentCode;

    @Column(name = "DepartmentName", length = 275)
    private String departmentName;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}