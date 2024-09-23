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
@Table(name = "temp_passenger_details", schema = "dvc_hanghai_nghiepvu_clone")
public class TempPassengerDetails {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "PassengerCode", length = 12)
    private String passengerCode;

    @Column(name = "FamilyName", length = 200)
    private String familyName;

    @Column(name = "GivenName", length = 200)
    private String givenName;

    @Column(name = "Nationality", length = 2)
    private String nationality;

    @Column(name = "BirthDay")
    private Date birthDay;

    @Column(name = "BirthPlace", length = 200)
    private String birthPlace;

    @Column(name = "TypeOfIdentity", length = 12)
    private String typeOfIdentity;

    @Column(name = "PassportExpiredDate")
    private Date passportExpiredDate;

    @Column(name = "SerialNumberOfIdentity", length = 20)
    private String serialNumberOfIdentity;

    @Column(name = "PortOfEmbarkation", length = 5)
    private String portOfEmbarkation;

    @Column(name = "PortOfDisembarkation", length = 5)
    private String portOfDisembarkation;

    @Column(name = "IsTransit")
    private Integer isTransit;

    @Column(name = "Gender", length = 20)
    private String gender;

    @Column(name = "GoAshore", length = 20)
    private String goAshore;

}