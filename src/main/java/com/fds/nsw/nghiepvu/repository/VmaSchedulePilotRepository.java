package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaSchedulePilotRepository extends JpaRepository<VmaSchedulePilot, Long> {
}