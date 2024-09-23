package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "temp_plant_quarantine", schema = "dvc_hanghai_nghiepvu_clone")
public class TempPlantQuarantine {
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

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 2)
    private String flagStateOfShip;

    @Column(name = "NameOfMaster")
    private String nameOfMaster;

    @Column(name = "DoctorName")
    private String doctorName;

    @Column(name = "NumberOfCrew")
    private Integer numberOfCrew;

    @Column(name = "NumberOfPassengers")
    private Integer numberOfPassengers;

    @Column(name = "LastPortOfCallCode", length = 5)
    private String lastPortOfCallCode;

    @Column(name = "NextPortOfCallCode", length = 5)
    private String nextPortOfCallCode;

    @Column(name = "FirstPortOfLoadingCode", length = 5)
    private String firstPortOfLoadingCode;

    @Column(name = "DateOfDeparture")
    private Instant dateOfDeparture;

    @Column(name = "PlantNameFirst", length = 500)
    private String plantNameFirst;

    @Column(name = "PlantNameBetween", length = 4000)
    private String plantNameBetween;

    @Column(name = "PlantNameThis", length = 500)
    private String plantNameThis;

    @Column(name = "SignPlace")
    private String signPlace;

    @Column(name = "SignDate")
    private Instant signDate;

    @Column(name = "MasterSigned")
    private Integer masterSigned;

    @Column(name = "MasterSignedImage", length = 1)
    private String masterSignedImage;

    @Column(name = "AttachedFile")
    private String attachedFile;

}