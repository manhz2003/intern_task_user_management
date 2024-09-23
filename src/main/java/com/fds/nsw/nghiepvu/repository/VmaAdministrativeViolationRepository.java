package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaAdministrativeViolationRepository extends JpaRepository<VmaAdministrativeViolation, Long> {
}