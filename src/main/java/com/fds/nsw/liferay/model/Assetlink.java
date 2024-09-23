package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "assetlink", schema = "dvc_hanghai_liferay_clone")
public class Assetlink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linkId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName", length = 75)
    private String userName;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "entryId1")
    private Long entryId1;

    @Column(name = "entryId2")
    private Long entryId2;

    @Column(name = "type_")
    private Integer type;

    @Column(name = "weight")
    private Integer weight;

}