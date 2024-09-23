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
@Table(name = "vma_transaction_department", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionDepartment {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "DepartmentCode", length = 12)
    private String departmentCode;

    @Column(name = "DepartmentName", length = 275)
    private String departmentName;

    @ColumnDefault("''")
    @Column(name = "TransactionSettlement", length = 12)
    private String transactionSettlement;

    @Column(name = "TransactionTypeVND", length = 12)
    private String transactionTypeVND;

    @Column(name = "TransactionTypeUSD", length = 12)
    private String transactionTypeUSD;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}