package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "aswmsg_messagequeue", schema = "dvc_hanghai_nghiepvu_clone")
public class AswmsgMessagequeue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id =0L;

    @Column(name = "version", length = 50)
    private String version;

    @Column(name = "messageid", length = 100)
    private String messageid;

    @Column(name = "sendername")
    private String sendername;

    @Column(name = "senderidentity", length = 13)
    private String senderidentity;

    @Column(name = "sendercountrycode", length = 2)
    private String sendercountrycode;

    @Column(name = "senderministrycode", length = 20)
    private String senderministrycode;

    @Column(name = "senderorganizationcode", length = 20)
    private String senderorganizationcode;

    @Column(name = "senderunitcode", length = 200)
    private String senderunitcode;

    @Column(name = "receivername")
    private String receivername;

    @Column(name = "receiveridentity", length = 13)
    private String receiveridentity;

    @Column(name = "receivercountrycode", length = 2)
    private String receivercountrycode;

    @Column(name = "receiverministrycode", length = 20)
    private String receiverministrycode;

    @Column(name = "receiverorganizationcode", length = 20)
    private String receiverorganizationcode;

    @Column(name = "receiverunitcode", length = 200)
    private String receiverunitcode;

    @Column(name = "documenttype", length = 100)
    private String documenttype;

    @Column(name = "type")
    private Integer type;

    @Column(name = "function", length = 3)
    private String function;

    @Column(name = "reference")
    private Long reference;

    @Column(name = "prereference")
    private Long prereference;

    @Column(name = "documentyear")
    private Integer documentyear;

    @Column(name = "senddate")
    private Date senddate;

    @Column(name = "signature")
    private String signature;

    @Column(name = "systemsignature")
    private String systemsignature;

    @Lob
    @Column(name = "allcontent")
    private String allcontent;

    @Column(name = "createdtime")
    private Date createdtime;

    @Column(name = "webservice")
    private String webservice;

    @Column(name = "validated")
    private Integer validated;

    @Column(name = "validationcode", length = 45)
    private String validationcode;

    @Column(name = "description", length = 450)
    private String description;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "solangui")
    private Integer solangui;

    @Column(name = "hosothutucid")
    private Long hosothutucid;

    @Column(name = "phieuxulyphuid")
    private Long phieuxulyphuid;

}