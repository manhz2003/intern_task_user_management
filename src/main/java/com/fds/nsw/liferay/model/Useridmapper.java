package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "useridmapper", schema = "dvc_hanghai_liferay_clone")
public class Useridmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userIdMapperId", nullable = false)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "type_", length = 75)
    private String type;

    @Column(name = "description", length = 75)
    private String description;

    @Column(name = "externalUserId", length = 75)
    private String externalUserId;

}