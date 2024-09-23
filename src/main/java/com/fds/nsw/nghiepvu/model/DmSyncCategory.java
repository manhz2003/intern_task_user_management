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
@Table(name = "dm_sync_category", schema = "dvc_hanghai_nghiepvu_clone")
public class DmSyncCategory {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "CategoryID", length = 50)
    private String categoryID;

    @Column(name = "CategoryDescription", length = 100)
    private String categoryDescription;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "ModifiedUser", length = 100)
    private String modifiedUser;

}