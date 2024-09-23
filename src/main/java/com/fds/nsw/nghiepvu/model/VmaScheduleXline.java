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
@Table(name = "vma_schedule_xline", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaScheduleXline {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "ShipOperatorCode", length = 30)
    private String shipOperatorCode;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "RouteDescription")
    private String routeDescription;

    @Column(name = "ScheduleYear")
    private Integer scheduleYear;

    @Column(name = "ScheduleMonth")
    private Integer scheduleMonth;

    @Column(name = "VoyageNumber")
    private Integer voyageNumber;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}