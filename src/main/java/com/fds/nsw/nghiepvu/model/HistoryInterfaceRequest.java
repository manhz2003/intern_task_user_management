package com.fds.nsw.nghiepvu.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "history_interface_request", schema = "dvc_hanghai_nghiepvu_clone")
public class HistoryInterfaceRequest {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;

    @Column(name = "RequestCode", length = 100)
    private String requestCode;

    @Column(name = "RequestDate")
    private Date requestDate;

    @Column(name = "RequestedDate")
    private Date requestedDate;

    @Column(name = "RequestDirection", length = 5)
    private String requestDirection;

    @Column(name = "RequestState")
    private Integer requestState;

    @Column(name = "RequestResponseTime")
    private Date requestResponseTime;

    @Column(name = "DocumentType", length = 12)
    private String documentType;

    @Column(name = "BusinessType", length = 12)
    private String businessType;

    @Column(name = "FunctionType", length = 12)
    private String functionType;

    @Column(name = "RequestContent")
    private String requestContent;

    @Column(name = "RequestVersion", length = 4)
    private String requestVersion;

    @Column(name = "SenderName", length = 600)
    private String senderName;

    @Column(name = "SenderIdentify")
    private String senderIdentify;

    @Column(name = "ReceiverName", length = 600)
    private String receiverName;

    @Column(name = "ReceiverIdentify", length = 30)
    private String receiverIdentify;

    @Column(name = "SendingDate")
    private Date sendingDate;

    @Column(name = "Remarks", length = 600)
    private String remarks;

    @Column(name = "OrganizationCode", length = 12)
    private String organizationCode;

    @Column(name = "LocCode", length = 12)
    private String locCode;

}