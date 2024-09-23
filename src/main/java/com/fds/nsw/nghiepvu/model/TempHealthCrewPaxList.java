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
@Table(name = "temp_health_crew_pax_list", schema = "dvc_hanghai_nghiepvu_clone")
public class TempHealthCrewPaxList {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "HealthCrewPassengerCode", length = 12)
    private String healthCrewPassengerCode;

    @Column(name = "PassengerOrCrewCode", length = 12)
    private String passengerOrCrewCode;

    @Column(name = "PassengerOrCrewName")
    private String passengerOrCrewName;

    @Column(name = "ClassOrRating")
    private Integer classOrRating;

    @Column(name = "StateVisitedCode", length = 2)
    private String stateVisitedCode;

    @Column(name = "PortVisitedCode", length = 5)
    private String portVisitedCode;

    @Column(name = "FromDate")
    private Date fromDate;

    @Column(name = "ToDate")
    private Date toDate;

}