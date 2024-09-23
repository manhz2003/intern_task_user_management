package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryUnitGeneralRepository extends JpaRepository<DmHistoryUnitGeneral, Integer> {
}