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
public class AssetentriesAssetcategoryId implements Serializable {
    private static final long serialVersionUID = 3565958570252074492L;
    @Column(name = "entryId", nullable = false)
    private Long entryId;

    @Column(name = "categoryId", nullable = false)
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AssetentriesAssetcategoryId entity = (AssetentriesAssetcategoryId) o;
        return Objects.equals(this.categoryId, entity.categoryId) &&
                Objects.equals(this.entryId, entity.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, entryId);
    }

}