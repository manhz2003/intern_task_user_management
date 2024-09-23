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
@Table(name = "dm_history_representative", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryRepresentative {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "RepCode", length = 12)
    private String repCode;

    @Column(name = "RepName", length = 500)
    private String repName;

    @Column(name = "RepNameVN", length = 500)
    private String repNameVN;

    @Column(name = "RepLevel")
    private Integer repLevel;

    @Column(name = "RepOrder")
    private Integer repOrder;

    @Column(name = "MaritimeCode", length = 12)
    private String maritimeCode;

    @Column(name = "IsDelete")
    private Integer isDelete = 0;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete = 0;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ModifiedDate", nullable = false)
    private Date modifiedDate;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "RequestedDate", nullable = false)
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}