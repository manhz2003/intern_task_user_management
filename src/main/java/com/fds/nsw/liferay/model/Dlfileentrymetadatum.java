package com.fds.nsw.liferay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dlfileentrymetadata", schema = "dvc_hanghai_liferay_clone")
public class Dlfileentrymetadatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileEntryMetadataId", nullable = false)
    private Long id;

    @Column(name = "uuid_", length = 75)
    private String uuid;

    @Column(name = "DDMStorageId")
    private Long dDMStorageId;

    @Column(name = "DDMStructureId")
    private Long dDMStructureId;

    @Column(name = "fileEntryTypeId")
    private Long fileEntryTypeId;

    @Column(name = "fileEntryId")
    private Long fileEntryId;

    @Column(name = "fileVersionId")
    private Long fileVersionId;

}