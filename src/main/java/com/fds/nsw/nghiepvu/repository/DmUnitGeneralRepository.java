package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmUnitGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmUnitGeneralRepository extends JpaRepository<DmUnitGeneral, Integer> {
}