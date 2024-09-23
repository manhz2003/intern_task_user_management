package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGtReportTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGtReportTemplateRepository extends JpaRepository<DmGtReportTemplate, Long> {
}