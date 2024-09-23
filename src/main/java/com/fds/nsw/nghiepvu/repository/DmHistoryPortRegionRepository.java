package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryPortRegionRepository extends JpaRepository<DmHistoryPortRegion, Integer> {
}