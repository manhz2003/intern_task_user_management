package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.AssetentriesAssetcategory;
import com.fds.nsw.liferay.model.AssetentriesAssetcategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetentriesAssetcategoryRepository extends JpaRepository<AssetentriesAssetcategory, AssetentriesAssetcategoryId> {
}