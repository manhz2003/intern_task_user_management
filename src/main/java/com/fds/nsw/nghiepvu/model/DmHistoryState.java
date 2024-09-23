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
@Table(name = "dm_history_state", schema = "dvc_hanghai_nghiepvu_clone")
public class DmHistoryState {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "StateCode", length = 2)
    private String stateCode;

    @Column(name = "StateName", length = 50)
    private String stateName;

    @Column(name = "Description", length = 50)
    private String description;

    @Column(name = "IsDelete")
    private Integer isDelete;

    @Column(name = "MarkedAsDelete")
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 575)
    private String syncVersion;

}