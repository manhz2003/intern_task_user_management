package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaServicePort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaServicePortRepository extends JpaRepository<VmaServicePort, Long> {
}