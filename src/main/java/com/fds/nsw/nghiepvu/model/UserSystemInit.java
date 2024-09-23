package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_system_init", schema = "dvc_hanghai_nghiepvu_clone")
public class UserSystemInit {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "valuedata", length = 50)
    private String valuedata;

    @Column(name = "keydata")
    private String keydata;

    @Column(name = "description")
    private String description;

}