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
@Table(name = "log_message_validation", schema = "dvc_hanghai_nghiepvu_clone")
public class LogMessageValidation {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "RequestDirection", nullable = false, length = 10)
    private String requestDirection;

    @Column(name = "RequestDate")
    private Date requestDate;

    @Column(name = "DocumentName")
    private Long documentName;

    @Column(name = "DocumentYear")
    private Integer documentYear;

    @Column(name = "DocumentType", length = 4)
    private String documentType;

    @Column(name = "TagProperty")
    private String tagProperty;

    @Column(name = "DataValidation", length = 300)
    private String dataValidation;

}