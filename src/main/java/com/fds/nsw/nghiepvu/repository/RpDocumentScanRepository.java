package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.RpDocumentScan;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RpDocumentScanRepository extends JpaRepository<RpDocumentScan, Long> {
}