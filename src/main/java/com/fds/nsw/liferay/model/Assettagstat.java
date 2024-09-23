package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assettagstats", schema = "dvc_hanghai_liferay_clone")
public class Assettagstat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagStatsId", nullable = false)
    private Long id;

    @Column(name = "tagId")
    private Long tagId;

    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "assetCount")
    private Integer assetCount;

}