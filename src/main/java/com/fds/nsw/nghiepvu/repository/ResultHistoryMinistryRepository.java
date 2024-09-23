package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultHistoryMinistryRepository extends JpaRepository<ResultHistoryMinistry, Long> {
}