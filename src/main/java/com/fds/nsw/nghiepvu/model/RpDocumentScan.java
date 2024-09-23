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
@Table(name = "rp_document_scan", schema = "dvc_hanghai_nghiepvu_clone")
public class RpDocumentScan {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "scanCode")
    private Long scanCode;

    @Column(name = "documentId")
    private Long documentId;

    @Column(name = "documentName")
    private Long documentName;

    @Column(name = "documentYear")
    private Integer documentYear;

    @Column(name = "scanValue")
    private Integer scanValue;

}