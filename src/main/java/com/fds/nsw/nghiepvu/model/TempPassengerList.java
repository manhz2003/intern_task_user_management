package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_passenger_list", schema = "dvc_hanghai_nghiepvu_clone")
public class TempPassengerList {
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

    @Column(name = "UserCreated")
    private String userCreated;

    @Column(name = "isArrival")
    private Integer isArrival;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "PortOfArrivalCode", length = 5)
    private String portOfArrivalCode;

    @Column(name = "DateOfArrival")
    private Date dateOfArrival;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 50)
    private String callSign;

    @Column(name = "VoyageNumber", length = 20)
    private String voyageNumber;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "ListPassengers")
    private Integer listPassengers;

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

    @Column(name = "NumberOfLeftPassengers")
    private Integer numberOfLeftPassengers;

    @Column(name = "NumberOfVNmeses")
    private Integer numberOfVNmeses;

    @Column(name = "NumberOfForeigners")
    private Integer numberOfForeigners;

    @Column(name = "TotalPassengers")
    private Integer totalPassengers;

}