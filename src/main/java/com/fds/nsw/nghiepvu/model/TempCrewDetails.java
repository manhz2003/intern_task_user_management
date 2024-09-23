package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "temp_crew_details", schema = "dvc_hanghai_nghiepvu_clone")
public class TempCrewDetails {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "CrewCode", length = 20)
    private String crewCode;

    @Column(name = "FamilyName")
    private String familyName;

    @Column(name = "GivenName")
    private String givenName;

    @Column(name = "RankCode")
    private String rankCode;

    @Column(name = "Nationality", length = 2)
    private String nationality;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "PlaceOfBirth")
    private String placeOfBirth;

    @Column(name = "PassportNumber", length = 30)
    private String passportNumber;

    @Column(name = "PassportTypeCode", length = 20)
    private String passportTypeCode;

    @Column(name = "Gender", length = 20)
    private String gender;

    @Column(name = "GoAshore", length = 20)
    private String goAshore;

}