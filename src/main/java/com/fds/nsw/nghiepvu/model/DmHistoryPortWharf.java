package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_history_port_wharf", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryPortWharf {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PortWharfCode", length = 12)
    private String portWharfCode;

    @Column(name = "PortWharfName", length = 50)
    private String portWharfName;

    @ColumnDefault("''")
    @Column(name = "PortWharfNameVN", length = 100)
    private String portWharfNameVN;

        @ColumnDefault("0")
    @Column(name = "PortWharfType") 
    private Integer portWharfType=0;

    @Column(name = "PortWharfPayment")
    private Integer portWharfPayment;

    @Column(name = "PortCode")
    private String portCode;

    @Column(name = "PortRegionCode", length = 12)
    private String portRegionCode;

    @Column(name = "PortHarbourCode", length = 12)
    private String portHarbourCode;

    @Column(name = "Note", length = 50)
    private String note;

    @ColumnDefault("''")
    @Column(name = "PortWharfShortName", length = 100)
    private String portWharfShortName;

    @ColumnDefault("0.00")
    @Column(name = "DWT", precision = 18, scale = 2)
    private BigDecimal dwt;

    @ColumnDefault("0.00")
    @Column(name = "LOA")
    private Double loa;

    @ColumnDefault("0.00")
    @Column(name = "MaxDraft")
    private Double maxDraft;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

        @ColumnDefault("0")
    @Column(name = "ManagedVinalines") 
    private Integer managedVinalines=0;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}