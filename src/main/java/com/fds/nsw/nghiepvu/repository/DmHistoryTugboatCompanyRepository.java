package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryTugboatCompanyRepository extends JpaRepository<DmHistoryTugboatCompany, Long> {
}