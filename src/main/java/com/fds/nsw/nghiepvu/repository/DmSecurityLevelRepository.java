package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmSecurityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmSecurityLevelRepository extends JpaRepository<DmSecurityLevel, Integer> {
}