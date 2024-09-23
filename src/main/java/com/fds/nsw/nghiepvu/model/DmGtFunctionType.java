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
@Table(name = "dm_gt_function_type", schema = "dvc_hanghai_nghiepvu_clone")
public class DmGtFunctionType {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "FunctionTypeCode", nullable = false, length = 10)
    private String functionTypeCode;

    @Column(name = "FunctionTypeName", nullable = false, length = 250)
    private String functionTypeName;

    @Column(name = "FunctionTypeNameVN", nullable = false, length = 250)
    private String functionTypeNameVN;

    @Column(name = "FunctionTypeOrder")
    private Integer functionTypeOrder;

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