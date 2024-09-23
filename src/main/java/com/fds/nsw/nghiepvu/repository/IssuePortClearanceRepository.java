package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IssuePortClearanceRepository extends JpaRepository<IssuePortClearance, Long> {
}