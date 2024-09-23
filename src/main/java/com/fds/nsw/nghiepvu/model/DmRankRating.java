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
@Table(name = "dm_rank_rating", schema = "dvc_hanghai_nghiepvu_clone")
public class DmRankRating {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "RankCode", nullable = false, length = 8)
    private String rankCode;

    @Column(name = "RankName", nullable = false, length = 250)
    private String rankName;

    @Column(name = "RankNameVN", nullable = false, length = 500)
    private String rankNameVN;

    @Column(name = "RankOrder")
    private Integer rankOrder;

    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete;

    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "SyncVersion", length = 500)
    private String syncVersion;

}