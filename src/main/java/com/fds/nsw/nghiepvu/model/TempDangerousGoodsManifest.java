package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_dangerous_goods_manifest", schema = "dvc_hanghai_nghiepvu_clone")
public class TempDangerousGoodsManifest {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode")
    private String requestCode;

    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "UserCreated", length = 20)
    private String userCreated;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "MasterName", length = 50)
    private String masterName;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "Callsign", length = 20)
    private String callsign;

    @Column(name = "VoyageNumber", length = 20)
    private String voyageNumber;

    @Column(name = "PortOfLoadingCode", length = 5)
    private String portOfLoadingCode;

    @Column(name = "PortOfDischargeCode", length = 5)
    private String portOfDischargeCode;

    @Column(name = "ShippingAgent", length = 250)
    private String shippingAgent;

    @Column(name = "AdditionalRemark", length = 500)
    private String additionalRemark;

    @Column(name = "ListDangerousGoods")
    private Integer listDangerousGoods;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage", length = 1)
    private String masterSignedImage;

    @Column(name = "AttachedFile", length = 200)
    private String attachedFile;

}