package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_conversion_type", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaConversionType {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "FunctionType", length = 20)
    private String functionType;

    @Lob
    @Column(name = "FunctionName")
    private String functionName;

    @ColumnDefault("1")
    @Column(name = "ConversionSequence")
    private Integer conversionSequence;

    @Column(name = "ConversionUnit", length = 50)
    private String conversionUnit;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}