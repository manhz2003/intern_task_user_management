package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.HistorySyncVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface HistorySyncVersionRepository extends JpaRepository<HistorySyncVersion, Long> {
}