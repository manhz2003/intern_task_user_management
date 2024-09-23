package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_tugboat_condition", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTugboatCondition {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "ShipConditionCode", length = 12)
    private String shipConditionCode;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "MinLOA")
    private Double minLOA;

    @Column(name = "MaxLOA")
    private Double maxLOA;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "TotalPower")
    private Double totalPower;

    @Column(name = "Power1")
    private Double power1;

    @Column(name = "Power2")
    private Double power2;

    @Column(name = "Power3")
    private Double power3;

    @Column(name = "Power4")
    private Double power4;

    @Column(name = "UnitPower")
    private String unitPower;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}