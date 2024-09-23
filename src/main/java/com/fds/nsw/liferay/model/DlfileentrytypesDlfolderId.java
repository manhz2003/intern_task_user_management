package com.fds.nsw.liferay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DlfileentrytypesDlfolderId implements Serializable {
    private static final long serialVersionUID = -6331648957516128831L;
    @Column(name = "fileEntryTypeId", nullable = false)
    private Long fileEntryTypeId;

    @Column(name = "folderId", nullable = false)
    private Long folderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DlfileentrytypesDlfolderId entity = (DlfileentrytypesDlfolderId) o;
        return Objects.equals(this.fileEntryTypeId, entity.fileEntryTypeId) &&
                Objects.equals(this.folderId, entity.folderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileEntryTypeId, folderId);
    }

}