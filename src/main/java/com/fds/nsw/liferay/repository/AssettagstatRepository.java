package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assettagstat;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssettagstatRepository extends JpaRepository<Assettagstat, Long> {
}