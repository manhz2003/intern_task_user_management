package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmHistoryPilotCompanyRepository extends JpaRepository<DmHistoryPilotCompany, Long> {
}