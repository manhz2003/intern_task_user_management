package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IssueAcceptanceRepository extends JpaRepository<IssueAcceptance, Long> {
}