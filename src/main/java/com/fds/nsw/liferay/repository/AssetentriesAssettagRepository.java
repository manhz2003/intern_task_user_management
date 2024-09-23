package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.AssetentriesAssettag;
import com.fds.nsw.liferay.model.AssetentriesAssettagId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetentriesAssettagRepository extends JpaRepository<AssetentriesAssettag, AssetentriesAssettagId> {
}