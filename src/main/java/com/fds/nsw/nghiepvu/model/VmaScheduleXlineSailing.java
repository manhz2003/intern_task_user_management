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
@Table(name = "vma_schedule_xline_sailing", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleXlineSailing {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ShipOperatorCode")
    private String shipOperatorCode;

    @Column(name = "ScheduleYear")
    private Integer scheduleYear;

    @Column(name = "ScheduleMonth")
    private Integer scheduleMonth;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "IMONumber")
    private String imoNumber;

    @Column(name = "CallSign")
    private String callSign;

    @Column(name = "RegistryNumber")
    private String registryNumber;

    @Column(name = "VoyageNo")
    private String voyageNo;

    @Column(name = "TimeOfArrival")
    private Date timeOfArrival;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @Column(name = "StateCode")
    private String stateCode;

    @Column(name = "ProvinceCode")
    private String provinceCode;

    @Column(name = "MaritimePortCode", length = 30)
    private String maritimePortCode;

    @Column(name = "PortGoingToStateName")
    private String portGoingToStateName;

    @Column(name = "PortGoingToCode")
    private String portGoingToCode;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}