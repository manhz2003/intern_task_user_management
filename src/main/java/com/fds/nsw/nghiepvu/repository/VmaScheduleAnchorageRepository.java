package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaScheduleAnchorageRepository extends JpaRepository<VmaScheduleAnchorage, Long> {
}