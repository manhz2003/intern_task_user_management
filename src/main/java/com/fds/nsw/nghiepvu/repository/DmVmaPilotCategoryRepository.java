package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVmaPilotCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVmaPilotCategoryRepository extends JpaRepository<DmVmaPilotCategory, Long> {
}