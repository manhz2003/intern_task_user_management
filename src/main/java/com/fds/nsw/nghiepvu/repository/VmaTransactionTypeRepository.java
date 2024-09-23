package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaTransactionTypeRepository extends JpaRepository<VmaTransactionType, Long> {
}