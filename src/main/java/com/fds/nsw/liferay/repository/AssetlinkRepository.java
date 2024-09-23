package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assetlink;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetlinkRepository extends JpaRepository<Assetlink, Long> {
}