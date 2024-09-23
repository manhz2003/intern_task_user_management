package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_schedule_pilot_list", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaSchedulePilotList {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "PilotCompanyCode", length = 30)
    private String pilotCompanyCode;

    @Column(name = "PilotCompanyName")
    private String pilotCompanyName;

    @Column(name = "PilotCode", length = 30)
    private String pilotCode;

    @Column(name = "PilotName")
    private String pilotName;

    @Column(name = "PilotCategoryCode", length = 18)
    private String pilotCategoryCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}