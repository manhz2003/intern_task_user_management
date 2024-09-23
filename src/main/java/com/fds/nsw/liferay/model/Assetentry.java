package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "assetentry", schema = "dvc_hanghai_liferay_clone")
public class Assetentry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId", nullable = false)
    private Long id;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName", length = 75)
    private String userName;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "classPK")
    private Long classPK;

    @Column(name = "classUuid", length = 75)
    private String classUuid;

    @Column(name = "classTypeId")
    private Long classTypeId;

    @Column(name = "visible")
    private Byte visible;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "publishDate")
    private Date publishDate;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "mimeType", length = 75)
    private String mimeType;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "summary")
    private String summary;

    @Lob
    @Column(name = "url")
    private String url;

    @Column(name = "layoutUuid", length = 75)
    private String layoutUuid;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "priority")
    private Double priority;

    @Column(name = "viewCount")
    private Integer viewCount;

}