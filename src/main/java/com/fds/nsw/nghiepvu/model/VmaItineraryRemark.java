package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_itinerary_remarks", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaItineraryRemark {
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

    @Column(name = "NoticeShipType")
    private Integer noticeShipType;

    @Column(name = "RequestDate")
    private Date requestDate;

    @Column(name = "RequestPerson")
    private String requestPerson;

    @Lob
    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "MarkedAsPending")
    private Integer markedAsPending;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

}