package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_history_ship_agency", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryShipAgency {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @ColumnDefault("''")
    @Column(name = "ShipAgencyCode", length = 20)
    private String shipAgencyCode;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyName", length = 200)
    private String shipAgencyName;

    @ColumnDefault("''")
    @Column(name = "ShipAgencyNameVN", length = 200)
    private String shipAgencyNameVN;

    @Column(name = "ShipAgencyShortName", length = 50)
    private String shipAgencyShortName;

    @Column(name = "TaxCode", length = 20)
    private String taxCode;

    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "CityCode", length = 12)
    private String cityCode;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "AddressVN", length = 200)
    private String addressVN;

    @Column(name = "Phone", length = 100)
    private String phone;

    @Column(name = "Fax", length = 100)
    private String fax;

    @Column(name = "Email", length = 50)
    private String email;

    @Lob
    @Column(name = "EmailRecipients")
    private String emailRecipients;

    @Column(name = "Description", length = 200)
    private String description;

    @Column(name = "Contacter", length = 100)
    private String contacter;

    @Column(name = "Position", length = 100)
    private String position;

    @Column(name = "ContactTell", length = 20)
    private String contactTell;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

    @Column(name = "Representative1")
    private String representative1;

    @Column(name = "RepresentativeTitle1")
    private String representativeTitle1;

    @Column(name = "Representative2")
    private String representative2;

    @Column(name = "RepresentativeTitle2")
    private String representativeTitle2;

}