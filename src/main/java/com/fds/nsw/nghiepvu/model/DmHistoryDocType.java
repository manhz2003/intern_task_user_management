package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dm_history_doc_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryDocType {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "DocumentTypeCode", nullable = false, length = 20)
    private String documentTypeCode;

    @Column(name = "DocumentType", nullable = false, length = 20)
    private String documentType;

    @Column(name = "DocumentTypeName", nullable = false, length = 250)
    private String documentTypeName;

    @ColumnDefault("0")
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete;

    @ColumnDefault("0")
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

}