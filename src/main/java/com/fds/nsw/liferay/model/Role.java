package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_", schema = "dvc_hanghai_liferay_clone")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false)
    private Long id;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "classPK")
    private Long classPK;

    @Column(name = "name", length = 75)
    private String name;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "type_")
    private Integer type;

    @Column(name = "subtype", length = 75)
    private String subtype;

}