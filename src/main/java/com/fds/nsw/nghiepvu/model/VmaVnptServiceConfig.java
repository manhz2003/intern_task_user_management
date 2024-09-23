package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_vnptserviceconfig", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaVnptServiceConfig {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 30)
    private String maritimeCode;

    @ColumnDefault("''")
    @Column(name = "TaxCode", length = 30)
    private String taxCode;

    @ColumnDefault("''")
    @Column(name = "PublishServiceSoapAddress", length = 200)
    private String publishServiceSoapAddress;

    @Column(name = "InvoiceAccount", length = 100)
    private String invoiceAccount;

    @Column(name = "InvoiceACpass", length = 100)
    private String invoiceACpass;

    @Column(name = "RemoteUsername", length = 100)
    private String remoteUsername;

    @Column(name = "WebservicePassword", length = 100)
    private String webservicePassword;

    @Column(name = "PatternCode", length = 30)
    private String patternCode;

    @Column(name = "SerialCode", length = 30)
    private String serialCode;

    @Lob
    @Column(name = "Remarks")
    private String remarks;

    @ColumnDefault("''")
    @Column(name = "TestSoapAddress", length = 200)
    private String testSoapAddress;

        @ColumnDefault("0")
    @Column(name = "TestMode") 
    private Integer testMode=0;

    @ColumnDefault("''")
    @Column(name = "TestInvoiceAccount", length = 100)
    private String testInvoiceAccount;

    @ColumnDefault("''")
    @Column(name = "TestInvoiceACpass", length = 100)
    private String testInvoiceACpass;

    @ColumnDefault("''")
    @Column(name = "TestRemoteUsername", length = 100)
    private String testRemoteUsername;

    @ColumnDefault("''")
    @Column(name = "TestWebservicePassword", length = 100)
    private String testWebservicePassword;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete = 0;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 600)
    private String syncVersion;

    @ColumnDefault("''")
    @Column(name = "TreasuryCode", length = 30)
    private String treasuryCode;

    @ColumnDefault("''")
    @Column(name = "TreasuryName", length = 150)
    private String treasuryName;

    @ColumnDefault("''")
    @Column(name = "TreasuryAccount", length = 30)
    private String treasuryAccount;

    @ColumnDefault("''")
    @Column(name = "CommercialBankCode", length = 30)
    private String commercialBankCode;

    @ColumnDefault("''")
    @Column(name = "CommercialBankName", length = 150)
    private String commercialBankName;

    @ColumnDefault("''")
    @Column(name = "CommercialBankAccount", length = 30)
    private String commercialBankAccount;

    @ColumnDefault("''")
    @Column(name = "SecondaryBankCode", length = 30)
    private String secondaryBankCode;

    @ColumnDefault("''")
    @Column(name = "SecondaryBankName", length = 150)
    private String secondaryBankName;

    @ColumnDefault("''")
    @Column(name = "SecondaryBankAccount", length = 30)
    private String secondaryBankAccount;

}