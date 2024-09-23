package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaCertificateRepository extends JpaRepository<VmaCertificate, Integer> {
}