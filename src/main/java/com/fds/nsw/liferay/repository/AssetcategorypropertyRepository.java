package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assetcategoryproperty;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetcategorypropertyRepository extends JpaRepository<Assetcategoryproperty, Long> {
}