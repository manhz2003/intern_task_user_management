package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_accident_list", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaAccidentList {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "AccidentCode", length = 30)
    private String accidentCode;

    @Column(name = "AccidentTime")
    private Date accidentTime;

    @Lob
    @Column(name = "AccidentRegion")
    private String accidentRegion;

    @Lob
    @Column(name = "AccidentBrief")
    private String accidentBrief;

    @Lob
    @Column(name = "AccidentConclusion")
    private String accidentConclusion;

    @Column(name = "AccidentType", length = 100)
    private String accidentType;

    @Column(name = "AccidentCriticalType", length = 100)
    private String accidentCriticalType;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber", length = 20)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @Column(name = "FlagStateOfShip", length = 12)
    private String flagStateOfShip;

    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Lob
    @Column(name = "DomesticShip")
    private String domesticShip;

    @Lob
    @Column(name = "InternationalShip")
    private String internationalShip;

    @Column(name = "DamageHumanLife", length = 50)
    private String damageHumanLife;

    @Column(name = "NumberOfDead", length = 50)
    private String numberOfDead;

    @Column(name = "NumberOfMissed", length = 50)
    private String numberOfMissed;

    @Column(name = "NumberOfInjured", length = 50)
    private String numberOfInjured;

    @Lob
    @Column(name = "DamageToCargo")
    private String damageToCargo;

    @Lob
    @Column(name = "RemarksOfCargo")
    private String remarksOfCargo;

    @Lob
    @Column(name = "DamageToShip")
    private String damageToShip;

    @Lob
    @Column(name = "RemarksOfShip")
    private String remarksOfShip;

    @Lob
    @Column(name = "DamageToEnvironment")
    private String damageToEnvironment;

    @Lob
    @Column(name = "RemarksOfEnvironment")
    private String remarksOfEnvironment;

    @Lob
    @Column(name = "DamageToMarineActivity")
    private String damageToMarineActivity;

    @Lob
    @Column(name = "RemarksOfMarineActivity")
    private String remarksOfMarineActivity;

    @Column(name = "AccidentOfficialNo", length = 50)
    private String accidentOfficialNo;

    @Column(name = "AccidentOfficialDate")
    private Date accidentOfficialDate;

    @Column(name = "PilotOnBoad", length = 20)
    private String pilotOnBoad;

    @Column(name = "MakeInvestigation", length = 20)
    private String makeInvestigation;

    @Column(name = "InvestigationDate")
    private Date investigationDate;

    @Column(name = "InvestigationOffice")
    private String investigationOffice;

    @Column(name = "f1AttachedReport", length = 30)
    private String f1AttachedReport;

    @Column(name = "f2AttachedReport", length = 30)
    private String f2AttachedReport;

    @Column(name = "f3AttachedReport", length = 30)
    private String f3AttachedReport;

    @Column(name = "f4AttachedReport", length = 30)
    private String f4AttachedReport;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}