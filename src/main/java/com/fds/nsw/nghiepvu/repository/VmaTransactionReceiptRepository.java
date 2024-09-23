package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaTransactionReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaTransactionReceiptRepository extends JpaRepository<VmaTransactionReceipt, Long> {
}