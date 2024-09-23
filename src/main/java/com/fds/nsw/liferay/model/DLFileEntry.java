package com.fds.nsw.liferay.model;

import com.fds.nsw.liferay.file.FileHandle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dlfileentry", schema = "dvc_hanghai_liferay_clone")
public class DLFileEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileEntryId", nullable = false)
    private Long fileEntryId;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName", length = 75)
    private String userName;

    @Column(name = "versionUserId")
    private Long versionUserId;

    @Column(name = "versionUserName", length = 75)
    private String versionUserName;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "repositoryId")
    private Long repositoryId;

    @Column(name = "folderId")
    private Long folderId;

    @Column(name = "name")
    private String name;

    @Column(name = "extension", length = 75)
    private String extension;

    @Column(name = "mimeType", length = 75)
    private String mimeType;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "extraSettings")
    private String extraSettings;

    @Column(name = "fileEntryTypeId")
    private Long fileEntryTypeId;

    @Column(name = "version", length = 75)
    private String version;

    @Column(name = "size_")
    private Long size;

    @Column(name = "readCount")
    private Integer readCount;

    @Column(name = "smallImageId")
    private Long smallImageId;

    @Column(name = "largeImageId")
    private Long largeImageId;

    @Column(name = "custom1ImageId")
    private Long custom1ImageId;

    @Column(name = "custom2ImageId")
    private Long custom2ImageId;


    public InputStream getContentStream() {
        try {
            return FileHandle.getContentStream(this);
        } catch (Exception e) {

        }

        return null;
    }

}