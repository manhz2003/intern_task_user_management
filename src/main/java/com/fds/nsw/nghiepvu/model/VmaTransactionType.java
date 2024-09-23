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
@Table(name = "vma_transaction_type", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionType {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "TransactionTypeCode", length = 12)
    private String transactionTypeCode;

    @Column(name = "TransactionTypeName", length = 275)
    private String transactionTypeName;

    @Column(name = "TransactionLevel")
    private Integer transactionLevel;

    @Column(name = "CurrencyCode", length = 10)
    private String currencyCode;

    @Column(name = "TransactionTypeShortName", length = 50)
    private String transactionTypeShortName;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}