package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assettagproperty;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssettagpropertyRepository extends JpaRepository<Assettagproperty, Long> {
}