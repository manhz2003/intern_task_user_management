package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistorySecurityLevelRepository extends JpaRepository<DmHistorySecurityLevel, Integer> {
}