package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_waste_list", schema = "dvc_hanghai_nghiepvu_clone")
public class TempWasteList {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 75)
    private String requestCode;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "TypeCode")
    private String typeCode;

    @Column(name = "TypeName")
    private String typeName;

    @Column(name = "Weight")
    private Double weight;

    @Column(name = "Unit", length = 75)
    private String unit;

}