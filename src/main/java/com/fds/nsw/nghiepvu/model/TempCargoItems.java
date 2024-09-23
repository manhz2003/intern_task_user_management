package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_cargo_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempCargoItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "CargoCategory", length = 12)
    private String cargoCategory;

    @Column(name = "CargoType", length = 12)
    private String cargoType;

    @Column(name = "CargoCode", length = 20)
    private String cargoCode;

    @Column(name = "Description", length = 1000)
    private String description;

    @Column(name = "Quantity")
    private double quantity;

    @Column(name = "Unit", length = 20)
    private String unit;

    @Column(name = "Sequence")
    private Integer sequence;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}