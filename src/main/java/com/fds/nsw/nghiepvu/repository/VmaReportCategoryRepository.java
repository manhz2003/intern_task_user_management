package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaReportCategoryRepository extends JpaRepository<VmaReportCategory, Long> {
}