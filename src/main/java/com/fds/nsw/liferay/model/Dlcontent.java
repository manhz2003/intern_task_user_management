package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dlcontent", schema = "dvc_hanghai_liferay_clone")
public class Dlcontent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentId", nullable = false)
    private Long id;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "repositoryId")
    private Long repositoryId;

    @Column(name = "path_")
    private String path;

    @Column(name = "version", length = 75)
    private String version;

    @Column(name = "data_")
    private byte[] data;

    @Column(name = "size_")
    private Long size;

}