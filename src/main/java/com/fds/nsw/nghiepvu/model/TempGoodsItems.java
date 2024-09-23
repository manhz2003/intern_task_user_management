package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "temp_goods_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempGoodsItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "BillOfLadingNo", length = 35)
    private String billOfLadingNo;

    @Column(name = "GoodItemCode", length = 12)
    private String goodItemCode;

    @Column(name = "GoodItemDescription", length = 4000)
    private String goodItemDescription;

    @Column(name = "NumberOfPackage")
    private long numberOfPackage;

    @Column(name = "KindOfPackages", length = 200)
    private String kindOfPackages;

    @Column(name = "MarksandNosofGoods", length = 200)
    private String marksandNosofGoods;

    @Column(name = "GrossWeight")
    private double grossWeight;

    @Column(name = "GrossWeightUnit", length = 20)
    private String grossWeightUnit;

    @Column(name = "Measurement")
    private double measurement;

    @Column(name = "MeasurementUnit", length = 20)
    private String measurementUnit;

}