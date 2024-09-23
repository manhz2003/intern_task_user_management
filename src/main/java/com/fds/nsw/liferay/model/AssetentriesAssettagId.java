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
public class AssetentriesAssettagId implements Serializable {
    private static final long serialVersionUID = 6873009572995418200L;
    @Column(name = "entryId", nullable = false)
    private Long entryId;

    @Column(name = "tagId", nullable = false)
    private Long tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AssetentriesAssettagId entity = (AssetentriesAssettagId) o;
        return Objects.equals(this.tagId, entity.tagId) &&
                Objects.equals(this.entryId, entity.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, entryId);
    }

}