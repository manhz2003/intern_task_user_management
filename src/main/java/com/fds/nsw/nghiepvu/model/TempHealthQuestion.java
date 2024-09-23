package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_health_question", schema = "dvc_hanghai_nghiepvu_clone")
public class TempHealthQuestion {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "HealthQuestionCode", length = 20)
    private String healthQuestionCode;

    @Column(name = "PersonDied")
    private Integer personDied;

    @Column(name = "PersonDiedNo")
    private Integer personDiedNo;

    @Column(name = "PersonDiedReport", length = 500)
    private String personDiedReport;

    @Column(name = "IsInfectious")
    private Integer isInfectious;

    @Column(name = "InfectiousReport", length = 500)
    private String infectiousReport;

    @Column(name = "IllPassengersGreaterNomal")
    private Integer illPassengersGreaterNomal;

    @Column(name = "IllPassengersNo")
    private Integer illPassengersNo;

    @Column(name = "IllPersonsOnBoard")
    private Integer illPersonsOnBoard;

    @Column(name = "IllPersonsReport")
    private Integer illPersonsReport;

    @Column(name = "MedicalPractitionerConsulted")
    private Integer medicalPractitionerConsulted;

    @Column(name = "MedicalTreatmentOrAdvice", length = 500)
    private String medicalTreatmentOrAdvice;

    @Column(name = "InfectionOrSpreadOfDisease")
    private Integer infectionOrSpreadOfDisease;

    @Column(name = "InfectionsReport", length = 500)
    private String infectionsReport;

    @Column(name = "IsSanitary")
    private Integer isSanitary;

    @Column(name = "SanitaryDes", length = 500)
    private String sanitaryDes;

    @Column(name = "IsStowaways")
    private Integer isStowaways;

    @Column(name = "JoinShip", length = 250)
    private String joinShip;

    @Column(name = "IsAnimal")
    private Integer isAnimal;

}