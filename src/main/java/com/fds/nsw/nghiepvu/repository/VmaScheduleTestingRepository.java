package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaScheduleTesting;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaScheduleTestingRepository extends JpaRepository<VmaScheduleTesting, Long> {
}