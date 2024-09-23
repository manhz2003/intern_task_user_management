package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfileentrytype", schema = "dvc_hanghai_liferay_clone")
public class Dlfileentrytype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileEntryTypeId", nullable = false)
    private Long id;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "groupId")
    private Long groupId;

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

    @Column(name = "name", length = 75)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

}