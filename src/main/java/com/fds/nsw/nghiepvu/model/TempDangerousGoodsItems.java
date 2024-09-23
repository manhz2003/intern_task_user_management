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
@Table(name = "temp_dangerous_goods_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempDangerousGoodsItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "DangerousGoodItemCode", length = 12)
    private String dangerousGoodItemCode;

    @Column(name = "RefNumber")
    private String refNumber;

    @Column(name = "MarksContainer", length = 50)
    private String marksContainer;

    @Column(name = "NumberContainer", length = 20)
    private String numberContainer;

    @Column(name = "NumberOfPackage", length = 20)
    private String numberOfPackage;

    @Column(name = "KindOfPackages")
    private String kindOfPackages;

    @Column(name = "ProperShippingName")
    private String properShippingName;

    @Column(name = "GoodClass", length = 20)
    private String goodClass;

    @Column(name = "UNNumber", length = 20)
    private String unNumber;

    @Column(name = "PackingGroup", length = 20)
    private String packingGroup;

    @Column(name = "SubsidiaryRisk", length = 20)
    private String subsidiaryRisk;

    @Column(name = "FlashPoint", length = 20)
    private String flashPoint;

    @Column(name = "MarinePollutant")
    private String marinePollutant;

    @Column(name = "GrossWeight")
    private double grossWeight;

    @Column(name = "Ems", length = 500)
    private String ems;

}