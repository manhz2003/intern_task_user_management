package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaTransactionDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaTransactionDepartmentRepository extends JpaRepository<VmaTransactionDepartment, Long> {
}