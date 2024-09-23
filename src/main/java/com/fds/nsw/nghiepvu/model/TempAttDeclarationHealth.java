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
@Table(name = "temp_att_declaration_health", schema = "dvc_hanghai_nghiepvu_clone")
public class TempAttDeclarationHealth {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "AttachmentCode", length = 20)
    private String attachmentCode;

    @Column(name = "PassengerOrCrewCode", length = 20)
    private String passengerOrCrewCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Sex")
    private Integer sex;

    @Column(name = "Nationality", length = 2)
    private String nationality;

    @Column(name = "PortJoinVesselCode", length = 5)
    private String portJoinVesselCode;

    @Column(name = "DateJoinVessel")
    private Date dateJoinVessel;

    @Column(name = "ClassOrRating")
    private Integer classOrRating;

    @Column(name = "NatureOfIllness")
    private String natureOfIllness;

    @Column(name = "DateOfOnsetOfSymptom")
    private Date dateOfOnsetOfSymptom;

    @Column(name = "ReportedMedicalOfficer")
    private Integer reportedMedicalOfficer;

    @Column(name = "DisposalOfCase")
    private String disposalOfCase;

    @Column(name = "MedicinesOrOther")
    private String medicinesOrOther;

    @Column(name = "Comments", length = 500)
    private String comments;

}