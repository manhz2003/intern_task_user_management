package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.ResultMinistry;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultMinistryRepository extends JpaRepository<ResultMinistry, Long> {
}