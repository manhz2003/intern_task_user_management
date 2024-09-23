package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempPlantQuarantineRepository extends JpaRepository<TempPlantQuarantine, Long> {
}