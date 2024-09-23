package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaPilotViolation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaPilotViolationRepository extends JpaRepository<VmaPilotViolation, Long> {
}