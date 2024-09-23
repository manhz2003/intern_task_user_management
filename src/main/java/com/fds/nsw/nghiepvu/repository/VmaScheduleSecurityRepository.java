package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaScheduleSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaScheduleSecurityRepository extends JpaRepository<VmaScheduleSecurity, Long> {
}