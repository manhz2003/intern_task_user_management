package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGtStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGtStatusRepository extends JpaRepository<DmGtStatus, Long> {
}