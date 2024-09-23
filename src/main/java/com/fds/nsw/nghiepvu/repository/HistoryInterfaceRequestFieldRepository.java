package com.fds.nsw.nghiepvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;

@Repository
public interface HistoryInterfaceRequestFieldRepository extends JpaRepository<HistoryInterfaceRequest, Long> {
}