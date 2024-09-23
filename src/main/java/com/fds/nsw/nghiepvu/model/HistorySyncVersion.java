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
@Table(name = "history_sync_version", schema = "dvc_hanghai_nghiepvu_clone")
public class HistorySyncVersion {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "TimeofPublish")
    private Date timeofPublish;

    @Column(name = "CategoryID", length = 50)
    private String categoryID;

    @Column(name = "SyncUnit", length = 600)
    private String syncUnit;

    @Column(name = "SyncUser", length = 600)
    private String syncUser;

    @Column(name = "SyncVersion", length = 5)
    private String syncVersion;

    @Column(name = "LatestValueDate")
    private Date latestValueDate;

}