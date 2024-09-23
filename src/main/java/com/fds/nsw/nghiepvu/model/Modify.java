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
@Table(name = "gt_modify", schema = "dvc_hanghai_nghiepvu_clone")
public class Modify {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "modifycode", length = 50)
    private String modifycode;

    @Column(name = "modifydesc", length = 600)
    private String modifydesc;

    @Column(name = "organization", length = 200)
    private String organization;

    @Column(name = "division", length = 200)
    private String division;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "modifydate", nullable = false)
    private Date modifydate;

    @Column(name = "documentname", nullable = false)
    private Long documentname;

    @Column(name = "documentyear", nullable = false)
    private Integer documentyear;

}