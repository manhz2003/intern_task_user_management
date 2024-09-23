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
@Table(name = "temp_person_list", schema = "dvc_hanghai_nghiepvu_clone")
public class TempPersonList {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode")
    private String requestCode;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age", length = 200)
    private String age;

    @Column(name = "NationalCode", length = 75)
    private String nationalCode;

    @Column(name = "NationalName")
    private String nationalName;

    @Column(name = "IlnessStatus", length = 500)
    private String ilnessStatus;

    @Column(name = "ReasonOfDead", length = 500)
    private String reasonOfDead;

    @Column(name = "PersonType")
    private Integer personType;

}