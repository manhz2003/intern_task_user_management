package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assetentry;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetentryRepository extends JpaRepository<Assetentry, Long> {
}