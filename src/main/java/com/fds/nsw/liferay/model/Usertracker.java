package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "usertracker", schema = "dvc_hanghai_liferay_clone")
public class Usertracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTrackerId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "sessionId", length = 200)
    private String sessionId;

    @Column(name = "remoteAddr", length = 75)
    private String remoteAddr;

    @Column(name = "remoteHost", length = 75)
    private String remoteHost;

    @Column(name = "userAgent", length = 200)
    private String userAgent;

}