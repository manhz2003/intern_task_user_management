package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaTransactionConversion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaTransactionConversionRepository extends JpaRepository<VmaTransactionConversion, Long> {
}