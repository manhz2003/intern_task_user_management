package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_pilot_violation", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaPilotViolation {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @ColumnDefault("''")
    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "PilotCode", length = 30)
    private String pilotCode;

    @Column(name = "ViolationDate")
    private Date violationDate;

    @Lob
    @Column(name = "ViolationDescription")
    private String violationDescription;

    @Lob
    @Column(name = "Troubleshooting")
    private String troubleshooting;

}