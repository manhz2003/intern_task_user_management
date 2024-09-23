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
@Table(name = "dm_dataitem", schema = "dvc_hanghai_nghiepvu_clone")
public class DmDataitem {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "datagroupid", nullable = false)
    private Integer datagroupid;

    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "node_1")
    private String node1;

    @Column(name = "node_2")
    private String node2;

    @Column(name = "node_3")
    private String node3;

    @Column(name = "node_4")
    private String node4;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "validatedfrom")
    private Date validatedfrom;

    @Column(name = "validatedto")
    private Date validatedto;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ColumnDefault("1")
    @Column(name = "order_", nullable = false)
    private Integer order;

    @Column(name = "short_name", length = 100)
    private String shortName;

}