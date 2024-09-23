package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.ResultCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultCompletionRepository extends JpaRepository<ResultCompletion, Long> {
}