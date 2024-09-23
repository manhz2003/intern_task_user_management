package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IssuePermissionForTransitRepository extends JpaRepository<IssuePermissionForTransit, Long> {
}