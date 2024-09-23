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
@Table(name = "vma_auditlog", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaAuditLog {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @ColumnDefault("0")
    @Column(name = "userid")
    private long userid;

    @ColumnDefault("''")
    @Column(name = "modifyuser")
    private String modifyuser;

    @ColumnDefault("''")
    @Column(name = "actionname")
    private String actionname;

    @Column(name = "actiontime")
    private Date actiontime;

    @ColumnDefault("''")
    @Column(name = "remarks", length = 600)
    private String remarks;

    @Column(name = "tablename")
    private String tablename;

    @Column(name = "keycode")
    private String keycode;

}