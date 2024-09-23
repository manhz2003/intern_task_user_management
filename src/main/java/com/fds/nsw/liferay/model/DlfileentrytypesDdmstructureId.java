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
public class DlfileentrytypesDdmstructureId implements Serializable {
    private static final long serialVersionUID = -8270873648844369817L;
    @Column(name = "fileEntryTypeId", nullable = false)
    private Long fileEntryTypeId;

    @Column(name = "structureId", nullable = false)
    private Long structureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DlfileentrytypesDdmstructureId entity = (DlfileentrytypesDdmstructureId) o;
        return Objects.equals(this.fileEntryTypeId, entity.fileEntryTypeId) &&
                Objects.equals(this.structureId, entity.structureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileEntryTypeId, structureId);
    }

}