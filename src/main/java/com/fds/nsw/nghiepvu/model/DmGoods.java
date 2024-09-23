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
@Table(name = "dm_goods", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGoods {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "GoodsItemCode", nullable = false, length = 5)
    private String goodsItemCode;

    @Column(name = "GoodsItemName", nullable = false, length = 200)
    private String goodsItemName;

    @Column(name = "GoodsItemNameVN", nullable = false, length = 200)
    private String goodsItemNameVN;

    @Column(name = "GoodsItemOrder", nullable = false, length = 200)
    private String goodsItemOrder;

    
    @Column(name = "IsDelete", nullable = false)
    private Integer isDelete = 0;

    
    @Column(name = "MarkedAsDelete", nullable = false)
    private Integer markedAsDelete = 0;

    @Column(name = "ModifiedDate", nullable = false)
    private Date modifiedDate;

    @Column(name = "RequestedDate", nullable = false)
    private Date requestedDate;

    @ColumnDefault("'1|'")
    @Column(name = "SyncVersion", nullable = false, length = 500)
    private String syncVersion;

}