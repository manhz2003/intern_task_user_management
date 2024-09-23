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
@Table(name = "user_port", schema = "dvc_hanghai_nghiepvu_clone")
public class UserPort {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "PortCode", length = 50)
    private String portCode;

    @Column(name = "userid")
    private int userId;

    @Column(name = "createdate")
    private Date createdate;

    @Column(name = "status")
    private Integer status;

    @ColumnDefault("''")
    @Column(name = "DepartmentCode", length = 12)
    private String departmentCode;

    @ColumnDefault("''")
    @Column(name = "RouteCode", length = 12)
    private String routeCode;

}