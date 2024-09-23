package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmSyncCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmSyncCategoryRepository extends JpaRepository<DmSyncCategory, Long> {
}