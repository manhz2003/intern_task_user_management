package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_receipt", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionReceipt {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @ColumnDefault("''")
    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @ColumnDefault("''")
    @Column(name = "TransactionTypeCode", length = 20)
    private String transactionTypeCode;

    @Lob
    @Column(name = "TransactionFunctionNote")
    private String transactionFunctionNote;

    @Lob
    @Column(name = "TransactionNoteV")
    private String transactionNoteV;

    @Lob
    @Column(name = "TransactionNoteE")
    private String transactionNoteE;

        @ColumnDefault("0")
    @Column(name = "SequenceNo") 
    private Integer sequenceNo=0;

}