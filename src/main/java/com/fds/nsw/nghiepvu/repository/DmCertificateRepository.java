package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmCertificateRepository extends JpaRepository<DmCertificate, Long> {
}