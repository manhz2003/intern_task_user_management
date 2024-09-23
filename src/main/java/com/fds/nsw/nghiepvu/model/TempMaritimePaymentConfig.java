package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_maritime_payment_config", schema = "dvc_hanghai_nghiepvu_clone")
public class TempMaritimePaymentConfig {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "MARITIMECODE", length = 75)
    private String maritimeCode;

    @Column(name = "MERCHANTCODE", length = 75)
    private String merchantCode;

    @Column(name = "MERCHANTKEY", length = 75)
    private String merchantKey;

    @Column(name = "MERCHANTNAME", length = 75)
    private String merchantName;

    @Column(name = "KEYPAYDOMAIN", length = 75)
    private String keypayDomain;

    @Column(name = "KEYPAYVERSION", length = 75)
    private String keypayVersion;

    @Column(name = "RETURNURL", length = 75)
    private String returnUrl;

}