package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempAnimalQuarantineRepository extends JpaRepository<TempAnimalQuarantine, Long> {
}