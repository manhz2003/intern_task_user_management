package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryArrivalPurposeRepository extends JpaRepository<DmHistoryArrivalPurpose, Integer> {
}