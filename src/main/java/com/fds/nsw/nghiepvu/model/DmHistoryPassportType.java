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
@Table(name = "dm_history_passport_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryPassportType {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "PassportTypeCode", nullable = false, length = 20)
    private String passportTypeCode;

    @Column(name = "PassportType", nullable = false, length = 20)
    private String passportType;

    @Column(name = "PassportTypeName", nullable = false, length = 250)
    private String passportTypeName;

    @Column(name = "PassportTypeNameVN", nullable = false, length = 250)
    private String passportTypeNameVN;

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

    @Column(name = "SyncVersion", length = 250)
    private String syncVersion;

}