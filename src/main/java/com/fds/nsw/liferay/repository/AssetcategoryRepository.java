package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assetcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetcategoryRepository extends JpaRepository<Assetcategory, Long> {
}