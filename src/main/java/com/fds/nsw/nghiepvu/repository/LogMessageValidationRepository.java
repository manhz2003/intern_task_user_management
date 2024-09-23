package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageValidationRepository extends JpaRepository<LogMessageValidation, Long> {
}