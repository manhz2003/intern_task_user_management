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
@Table(name = "temp_ship_security_port_items", schema = "dvc_hanghai_nghiepvu_clone")
public class TempShipSecurityPortItems {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "ShipSecurityPortItemCode", length = 50)
    private String shipSecurityPortItemCode;

    @Column(name = "PortCode", length = 50)
    private String portCode;

    @Column(name = "DateArrival")
    private Date dateArrival;

    @Column(name = "DateDeparture")
    private Date dateDeparture;

    @Column(name = "SecurityLevelCode", length = 12)
    private String securityLevelCode;

}