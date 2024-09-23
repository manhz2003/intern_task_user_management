package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaPaymentAdvance;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaPaymentAdvanceRepository extends JpaRepository<VmaPaymentAdvance, Long> {
}