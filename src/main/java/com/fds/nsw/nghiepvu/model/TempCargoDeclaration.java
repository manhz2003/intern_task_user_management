package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_cargo_declaration", schema = "dvc_hanghai_nghiepvu_clone")
public class TempCargoDeclaration {
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

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "isArrival")
    private Integer isArrival;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber", length = 20)
    private String imoNumber;

    @Column(name = "Callsign", length = 20)
    private String callsign;

    @Column(name = "VoyageNumber", length = 30)
    private String voyageNumber;

    @Column(name = "PortReport", length = 20)
    private String portReport;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "NameOfMaster")
    private String nameOfMaster;

    @Column(name = "PortOfLoading", length = 20)
    private String portOfLoading;

    @Column(name = "ListGoods")
    private Integer listGoods;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Date signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage", length = 1)
    private String masterSignedImage;

    @Column(name = "AttachedFile")
    private String attachedFile;

}