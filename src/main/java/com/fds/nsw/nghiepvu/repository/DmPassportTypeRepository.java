package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmPassportType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmPassportTypeRepository extends JpaRepository<DmPassportType, Integer> {
}