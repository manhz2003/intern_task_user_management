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
@Table(name = "vma_anninh_cangbien", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaAnNinhCangBien {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MaritimeCode", length = 75)
    private String maritimeCode;

    @Column(name = "CertificateNo", length = 50)
    private String certificateNo;

    @Column(name = "IssueDate")
    private Date issueDate;

    @Column(name = "ExpireDate")
    private Date expireDate;

    @Column(name = "IssuePlace")
    private String issuePlace;

    @Column(name = "AttachFileId")
    private Integer attachFileId;

    @Column(name = "PortFacilityName")
    private String portFacilityName;

    @Column(name = "PortFacilityAddress")
    private String portFacilityAddress;

    @Column(name = "SecurityName")
    private String securityName;

    @Column(name = "SecurityContact")
    private String securityContact;

    @Column(name = "ExpireStatus")
    private String expireStatus;

        @ColumnDefault("0")
    @Column(name = "FirstAuthorized") 
    private Integer firstAuthorized=0;

    @Column(name = "FirstVerificationDate")
    private Date firstVerificationDate;

        @ColumnDefault("0")
    @Column(name = "SecondAuthorized") 
    private Integer secondAuthorized=0;

    @Column(name = "SecondVerificationDate")
    private Date secondVerificationDate;

        @ColumnDefault("0")
    @Column(name = "ThirdAuthorized") 
    private Integer thirdAuthorized=0;

    @Column(name = "ThirdVerificationDate")
    private Date thirdVerificationDate;

        @ColumnDefault("0")
    @Column(name = "FourthAuthorized") 
    private Integer fourthAuthorized=0;

    @Column(name = "FourthVerificationDate")
    private Date fourthVerificationDate;

        @ColumnDefault("0")
    @Column(name = "PassengerShipApproval") 
    private Integer passengerShipApproval=0;

        @ColumnDefault("0")
    @Column(name = "PassengerHighSpeedApproval") 
    private Integer passengerHighSpeedApproval=0;

        @ColumnDefault("0")
    @Column(name = "CargoHighSpeedApproval") 
    private Integer cargoHighSpeedApproval=0;

        @ColumnDefault("0")
    @Column(name = "BulkCarrierApproval") 
    private Integer bulkCarrierApproval=0;

        @ColumnDefault("0")
    @Column(name = "OilTankerApproval") 
    private Integer oilTankerApproval=0;

        @ColumnDefault("0")
    @Column(name = "ChemicalTankerApproval") 
    private Integer chemicalTankerApproval=0;

        @ColumnDefault("0")
    @Column(name = "GasCarrierApproval") 
    private Integer gasCarrierApproval=0;

        @ColumnDefault("0")
    @Column(name = "MobileOffshoreApproval") 
    private Integer mobileOffshoreApproval=0;

        @ColumnDefault("0")
    @Column(name = "OtherCargoShipApproval") 
    private Integer otherCargoShipApproval=0;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}