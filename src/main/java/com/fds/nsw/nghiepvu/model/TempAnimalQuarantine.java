package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_animal_quarantine", schema = "dvc_hanghai_nghiepvu_clone")
public class TempAnimalQuarantine {
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

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "NumberOfCrew")
    private Integer numberOfCrew;

    @Column(name = "NumberOfPassengers")
    private Integer numberOfPassengers;

    @Column(name = "LastPortOfCallCode", length = 20)
    private String lastPortOfCallCode;

    @Column(name = "NextPortOfCallCode", length = 20)
    private String nextPortOfCallCode;

    @Column(name = "AnimalNameFirst", length = 500)
    private String animalNameFirst;

    @Column(name = "AnimalNameBetween", length = 4000)
    private String animalNameBetween;

    @Column(name = "AnimalNameThis", length = 500)
    private String animalNameThis;

    @Column(name = "NameOfMaster", length = 100)
    private String nameOfMaster;

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