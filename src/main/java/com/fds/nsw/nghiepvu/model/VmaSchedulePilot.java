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
@Table(name = "vma_schedule_pilot", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaSchedulePilot {
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

    @Column(name = "CertificateNo", length = 75)
    private String certificateNo;

    @Column(name = "NoticeShipType")
    private Integer noticeShipType;

    @Column(name = "PilotDateFrom")
    private Date pilotDateFrom;

    @Column(name = "PilotDateTo")
    private Date pilotDateTo;

    @Column(name = "AnchoringPortHarbourCode", length = 12)
    private String anchoringPortHarbourCode;

    @Column(name = "AnchoringPortWharfCode", length = 6)
    private String anchoringPortWharfCode;

    @Column(name = "ShiftingPortRegionCode", length = 5)
    private String shiftingPortRegionCode;

    @Column(name = "ShiftingPortHarbourCode", length = 12)
    private String shiftingPortHarbourCode;

    @Column(name = "ShiftingPortWharfCode", length = 6)
    private String shiftingPortWharfCode;

        @ColumnDefault("0")
    @Column(name = "itineraryScheduleId") 
    private Long itineraryScheduleId=0L;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}