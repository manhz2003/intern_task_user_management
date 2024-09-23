package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_ship_stores_declaration", schema = "dvc_hanghai_nghiepvu_clone")
public class TempShipStoresDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id = 0L;

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

    @Column(name = "isArrival")
    private Integer isArrival;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber", length = 50)
    private String iMONumber;

    @Column(name = "Callsign", length = 20)
    private String callsign;

    @Column(name = "VoyageNumber", length = 20)
    private String voyageNumber;

    @Column(name = "NationalityOfShip", length = 2)
    private String nationalityOfShip;

    @Column(name = "PortOfArrivalCode", length = 5)
    private String portOfArrivalCode;

    @Column(name = "DateOfArrival")
    private Date dateOfArrival;

    @Column(name = "LastPortOfCallCode", length = 5)
    private String lastPortOfCallCode;

    @Column(name = "NumberOfPersonsOnBoat")
    private Integer numberOfPersonsOnBoat;

    @Column(name = "PeriodOfStay", length = 50)
    private String periodOfStay;

    @Column(name = "PeriodOfStayUnit", length = 12)
    private String periodOfStayUnit;

    @Column(name = "NameOfMaster", length = 100)
    private String nameOfMaster;

    @Column(name = "ListShipsStores")
    private Integer listShipsStores;

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