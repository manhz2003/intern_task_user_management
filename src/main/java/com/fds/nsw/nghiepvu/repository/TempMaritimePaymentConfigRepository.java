package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempMaritimePaymentConfigRepository extends JpaRepository<TempMaritimePaymentConfig, Long> {
}