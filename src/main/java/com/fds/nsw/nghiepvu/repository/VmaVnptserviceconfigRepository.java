package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaVnptserviceconfigRepository extends JpaRepository<VmaVnptServiceConfig, Long> {
}