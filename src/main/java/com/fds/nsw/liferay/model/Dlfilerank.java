package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfilerank", schema = "dvc_hanghai_liferay_clone")
public class Dlfilerank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileRankId", nullable = false)
    private Long id;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "fileEntryId")
    private Long fileEntryId;

}