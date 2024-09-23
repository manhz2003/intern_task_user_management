package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "account_", schema = "dvc_hanghai_liferay_clone")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName", length = 75)
    private String userName;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "parentAccountId")
    private Long parentAccountId;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "legalName", length = 75)
    private String legalName;

    @Column(name = "legalId", length = 75)
    private String legalId;

    @Column(name = "legalType", length = 75)
    private String legalType;

    @Column(name = "sicCode", length = 75)
    private String sicCode;

    @Column(name = "tickerSymbol", length = 75)
    private String tickerSymbol;

    @Column(name = "industry", length = 75)
    private String industry;

    @Column(name = "type_", length = 75)
    private String type;

    @Column(name = "size_", length = 75)
    private String size;

}