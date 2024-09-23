package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_vma_tugboat", schema = "dvc_hanghai_nghiepvu_clone")
public class DmVmaTugboat {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "TugboatCompanyCode", length = 75)
    private String tugboatCompanyCode;

    @Lob
    @Column(name = "TugboatCompanyName")
    private String tugboatCompanyName;

    @Column(name = "ShipCode", length = 75)
    private String shipCode;

    @ColumnDefault("''")
    @Column(name = "ShipName")
    private String shipName;

    @Column(name = "TugboatShortName", length = 50)
    private String tugboatShortName;

    @Column(name = "CompanyAddress", length = 75)
    private String companyAddress;

    @Column(name = "Power")
    private Double power;

    @Column(name = "LOA")
    private Double loa;

    @Column(name = "Breadth")
    private Double breadth;

    @Column(name = "ClearanceHeight")
    private Double clearanceHeight;

    @Column(name = "Displacement")
    private Double displacement;

    @Column(name = "UnitPower", length = 6)
    private String unitPower;

    @Column(name = "VndUnitPrice")
    private Double vndUnitPrice;

    @Column(name = "UsdUnitPrice")
    private Double usdUnitPrice;

    @Lob
    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 75)
    private String syncVersion;

    @ColumnDefault("0.00")
    @Column(name = "GT", precision = 18, scale = 2)
    private BigDecimal gt;

    @ColumnDefault("0.00")
    @Column(name = "NT", precision = 18, scale = 2)
    private BigDecimal nt;

    @ColumnDefault("0.00")
    @Column(name = "DWT", precision = 18, scale = 2)
    private BigDecimal dwt;

    @Column(name = "UnitGRT", length = 10)
    private String unitGRT;

    @Column(name = "UnitNT", length = 10)
    private String unitNT;

    @Column(name = "UnitDWT", length = 10)
    private String unitDWT;

    @Column(name = "TugboatExpiredDate")
    private Date tugboatExpiredDate;

}