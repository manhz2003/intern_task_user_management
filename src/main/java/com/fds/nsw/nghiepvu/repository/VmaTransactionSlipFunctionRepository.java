package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaTransactionSlipFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaTransactionSlipFunctionRepository extends JpaRepository<VmaTransactionSlipFunction, Long> {
}