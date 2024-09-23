package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_sign", schema = "dvc_hanghai_nghiepvu_clone")
public class UserSign {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "userid")
    private long userId;

    @Column(name = "portcode")
    private String portcode;

    @Column(name = "title")
    private String title;

    @Column(name = "representative", length = 600)
    private String representative;

    @Column(name = "filechukyid")
    private long filechukyid;

    @Column(name = "filecondauid")
    private long filecondauid;

    @Column(name = "filechungthusoid")
    private long filechungthusoid;

    @Column(name = "filechungthusohsm")
    private String filechungthusohsm;

    @Column(name = "modifydate")
    private Date modifydate;

    @Column(name = "modifyuser")
    private String modifyuser;

}