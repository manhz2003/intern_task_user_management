package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface HistoryInterfaceRequestRepository extends JpaRepository<HistoryInterfaceRequest, Long> {
}