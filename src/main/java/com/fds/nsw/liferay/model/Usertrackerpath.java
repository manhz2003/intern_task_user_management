package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "usertrackerpath", schema = "dvc_hanghai_liferay_clone")
public class Usertrackerpath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTrackerPathId", nullable = false)
    private Long id;

    @Column(name = "userTrackerId")
    private Long userTrackerId;

    @Lob
    @Column(name = "path_")
    private String path;

    @Column(name = "pathDate")
    private Date pathDate;

}