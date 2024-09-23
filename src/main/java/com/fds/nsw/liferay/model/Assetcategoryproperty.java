package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "assetcategoryproperty", schema = "dvc_hanghai_liferay_clone")
public class Assetcategoryproperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryPropertyId", nullable = false)
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

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "key_", length = 75)
    private String key;

    @Column(name = "value", length = 75)
    private String value;

}